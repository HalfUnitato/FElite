package de.felite.util.fileIOComponent.fileIOxml

import java.io._

import com.google.inject.Guice
import com.google.inject.name.Names
import de.felite.FEliteModule
import de.felite.controller.GameControllerInterface
import de.felite.controller.state.game
import de.felite.controller.state.game.GameStateString
import de.felite.controller.state.player.PlayerState
import de.felite.model.entity.obstacle.SimpleObstacle
import de.felite.model.{Field, SoldierFactory}
import de.felite.util.fileIOComponent.FileIOInterface
import net.codingwell.scalaguice.InjectorExtensions._

import scala.xml.{Elem, PrettyPrinter}

class FileIO extends FileIOInterface {

  override def load(controller: GameControllerInterface, fileName: String, fieldSize: Int): Field = {
    var field: Field = null
    val file = scala.xml.XML.loadFile(fileName + ".xml")
    var size = fieldSize
    if(size == -1) {
      size = (file \\ "field" \ "@size").text.toInt
    }
    val injector = Guice.createInjector(new FEliteModule)
    size match {
      case 4 => field = injector.instance[Field](Names.named("small"))
      case 6 => field = injector.instance[Field](Names.named("max"))
      case _ => field = injector.instance[Field](Names.named("middle"))
    }
    val gameNode = file \\ "game"
    val currentPlayer = (gameNode \ "@currentPlayer").text.toInt
    controller.currentPlayer = if (currentPlayer == 1) controller.player1 else controller.player2
    PlayerState.state = if (currentPlayer == 1) GameStateString.P1 else game.GameStateString.P2

    val obstacleNode = file \\ "obstacle"
    for (obstacle <- obstacleNode) {
      val y: Int = (obstacle \ "@row").text.toInt
      val x: Int = (obstacle \ "@col").text.toInt

      if (size != -1 || size > x && size > y) {
        val typ: String = obstacle.text.trim

        typ match {
          case "r" => field.setCell(SimpleObstacle(typ.charAt(0), _walkthrough = false), x, y)
          case "t" => field.setCell(SimpleObstacle(typ.charAt(0), _walkthrough = false), x, y)
          case _ => field.setCell(SimpleObstacle(typ.charAt(0), _walkthrough = true), x, y) // default
        }
      }
    }

    val troopNode = file \\ "troop"
    for (troop <- troopNode) {
      val y: Int = (troop \ "@row").text.toInt
      val x: Int = (troop \ "@col").text.toInt

      if (size != -1 || size > x && size > y) {
        val typ: String = troop.text.trim
        val health: Int = (troop \ "@health").text.toInt
        val player: Int = (troop \ "@player").text.toInt
        val owner = if (player == 1) controller.player1
        else controller.player2

        val troopVar = SoldierFactory(typ = typ.charAt(0),
          health = health,
          player = owner)

        field.setCell(troopVar, x, y)
        owner.addPlayerTroop(troopVar)
      }
    }

    controller.field = field
    field
  }

  override def store(field: Field, controller: GameControllerInterface): Unit = {
    val pw = new PrintWriter(new File("field.xml"))
    val pp = new PrettyPrinter(120, 4)
    val xml = pp.format(gameToXML(field, controller), scala.xml.TopScope)
    pw.write(xml)
    pw.close()
  }

  def gameToXML(field: Field, controller: GameControllerInterface): Elem = {
    <game currentPlayer={controller.currentPlayer._number.toString}>
      {fieldToXML(field)}
    </game>
  }

  def fieldToXML(field: Field): Elem = {
    <field size={field.getScale.toString}>
      {for {
      y <- 0 until field.getScale
      x <- 0 until field.getScale
    } yield field.getCell(x, y).toXML(x, y)}
    </field>
  }
}