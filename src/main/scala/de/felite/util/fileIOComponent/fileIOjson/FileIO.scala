package de.felite.util.fileIOComponent.fileIOjson

import java.io.{File, PrintWriter}

import com.google.inject.Guice
import com.google.inject.name.Names
import de.felite.FEliteModule
import de.felite.controller.GameControllerInterface
import de.felite.controller.state.game
import de.felite.controller.state.game.GameStateString
import de.felite.controller.state.player.PlayerState
import de.felite.model.{Field, Player, SoldierFactory, Troop}
import de.felite.model.entity.figure.FootPatrol
import de.felite.model.entity.obstacle.SimpleObstacle
import de.felite.util.fileIOComponent.FileIOInterface
import play.api.libs.json._
import net.codingwell.scalaguice.InjectorExtensions._

import scala.io.Source
import scala.xml.PrettyPrinter

class FileIO extends FileIOInterface {
  override def load(controller: GameControllerInterface, fileName: String, fieldSize: Int): Field = {
    var field: Field = null
    val srcsrc = Source.fromFile(fileName)
    val src = srcsrc.getLines().mkString
    val file = Json.parse(src)
    var size = fieldSize

    if (fieldSize == -1) {
      size = (file \ "size").get.toString().toInt
    }

    val injector = Guice.createInjector(new FEliteModule)
    size match {
      case 4 => field = injector.instance[Field](Names.named("small"))
      case 6 => field = injector.instance[Field](Names.named("max"))
      case _ => field = injector.instance[Field](Names.named("middle"))
    }

    val currentPlayer = (file \ "currentPlayer").get.toString().toInt
    controller.currentPlayer = if (currentPlayer == 1) controller.player1 else controller.player2
    PlayerState.state = if (currentPlayer == 1) GameStateString.P1 else game.GameStateString.P2

    // parse obstacles
    val ofile = file \ "obstacles"
    for (i <- ofile.as[JsArray].value.indices) {
      val row = (ofile \\ "row") (i).as[Int]
      val col = (ofile \\ "col") (i).as[Int]
      val sign = (ofile \\ "sign") (i).as[String]

      sign match {
        case "r" => field.setCell(SimpleObstacle(sign.charAt(0), _walkthrough = false), row, col)
        case "t" => field.setCell(SimpleObstacle(sign.charAt(0), _walkthrough = false), row, col)
        case _ => field.setCell(SimpleObstacle(sign.charAt(0), _walkthrough = true), row, col) // default

      }

    }

    val tfile = file \ "troops"
    for (i <- tfile.as[JsArray].value.indices) {
      val row = (tfile \\ "row") (i).as[Int]
      val col = (tfile \\ "col") (i).as[Int]
      val sign = (tfile \\ "sign") (i).as[String]
      val health = (tfile \\ "health") (i).as[Int]
      val player = (tfile \\ "player") (i).as[Int]
      val owner = if (player == 1) controller.player1 else controller.player2

      val troop = SoldierFactory.create(typ = sign.charAt(0),
        health = health,
        player = owner)
      field.setCell(troop, row, col)
      owner.addPlayerTroop(troop)
    }


    controller.field = field
    field
  }


  override def store(field: Field, controller: GameControllerInterface): Unit = {
    val pw = new PrintWriter(new File("game.json"))
    pw.write(Json.prettyPrint(gameToJSON(field, controller)))
    pw.close()
  }

  def gameToJSON(field: Field, controller: GameControllerInterface): JsValue = {
    val obstList: List[JsValue] = Nil//not wokring...
    val troopList: List[JsValue] = Nil
    for {
      y <- 0 until field.getScale
      x <- 0 until field.getScale
    } {
      val cell = field.getCell(x, y)
      val cellStr = cell.sign.toString
      if (!cellStr.equals("s") || !cellStr.equals("a")) {
        obstList :+ Json.obj(
          "row" -> x,
          "col" -> y,
          "sign" -> cellStr)
      } else {
       troopList :+ Json.obj(
          "row" -> x,
          "col" -> y,
          "sign" -> cellStr,
          "health" -> cell.asInstanceOf[Troop].health(),
          "player" -> cell.asInstanceOf[Troop].owner()._number
        )
      }
    }
    println(obstList)
    Json.obj(
      "currentPlayer" -> controller.currentPlayer._number,
      "size" -> field.getScale,
      "obstacles" -> obstList,
      "troops" -> troopList
    )
  }
}
