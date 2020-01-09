package de.felite.util.fileIOComponent.fileIOxml

import java.io._

import com.google.inject.Guice
import com.google.inject.name.Names
import de.felite.FEliteModule
import de.felite.controller.GameControllerInterface
import de.felite.model.entity.obstacle.SimpleObstacle
import de.felite.model.{Field, SoldierFactory}
import de.felite.util.fileIOComponent.FileIOInterface
import net.codingwell.scalaguice.InjectorExtensions._

import scala.xml.{Elem, PrettyPrinter}

class FileIO extends FileIOInterface {
  override def load(controller: GameControllerInterface): Field = {
    var field: Field = null
    val file = scala.xml.XML.loadFile("field.xml")
    val size = (file \\ "field" \ "@size").text.toInt
    val injector = Guice.createInjector(new FEliteModule)
    size match {
      case 4 => field = injector.instance[Field](Names.named("small"))
      case 6 => field = injector.instance[Field](Names.named("max"))
      case _ => field = injector.instance[Field](Names.named("middle"))
    }

    val obstacleNode = file \\ "obstacle"
    for (obstacle <- obstacleNode) {
      val y: Int = (obstacle \ "@row").text.toInt
      val x: Int = (obstacle \ "@col").text.toInt
      val typ: String = obstacle.text.trim
      typ match {
        case "r" => field.setCell(SimpleObstacle(typ.charAt(0), x, y, _walkthrough = false), x, y)
        case "t" => field.setCell(SimpleObstacle(typ.charAt(0), x, y, _walkthrough = false), x, y)
        case _ => field.setCell(SimpleObstacle(typ.charAt(0), x, y, _walkthrough = true), x, y) // default
      }
    }
    val troopNode = file \\ "troop"
    for (troop <- troopNode) {
      val y: Int = (troop \ "@row").text.toInt
      val x: Int = (troop \ "@col").text.toInt
      val typ: String = troop.text.trim
      val health: Int = (troop \ "@health").text.toInt
      val player: Int = (troop \ "@player").text.toInt
      val owner = if (player == 1) controller.player1
      else controller.player2

      val troopVar = SoldierFactory.create(typ = typ.charAt(0),
        pos = (x, y), health = health,
        player = owner)

      field.setCell(troopVar, x, y)
      owner.addPlayerTroop(troopVar)
    }

    controller.field = field
    field
  }

  override def store(field: Field): Unit = {
    val pw = new PrintWriter(new File("field.xml"))
    val pp = new PrettyPrinter(120, 4)
    val xml = pp.format(fieldToXML(field), scala.xml.TopScope)
    pw.write(xml)
    pw.close()
  }

  def fieldToXML(field: Field): Elem = {
    <field size={field.getScale.toString}>
      {for {
      y <- 0 until field.getScale
      x <- 0 until field.getScale
    } yield field.getCell(x, y).toXML(x,y)}
    </field>
  }
}