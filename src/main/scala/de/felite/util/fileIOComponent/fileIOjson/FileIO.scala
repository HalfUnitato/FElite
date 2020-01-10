package de.felite.util.fileIOComponent.fileIOjson

import java.io.{File, PrintWriter}

import de.felite.controller.GameControllerInterface
import de.felite.model.Field
import de.felite.model.entity.figure.FootPatrol
import de.felite.util.fileIOComponent.FileIOInterface
import play.api.libs.json._

import scala.io.Source
import scala.xml.PrettyPrinter

class FileIO extends FileIOInterface {
  override def load(controller: GameControllerInterface, fileName: String, size: Int): Field = {
    val field = null
    val src = Source.fromFile(fileName)
    val srcstr = src.getLines().mkString
    val jsonFile = Json.parse(srcstr)

    field
  }

  override def store(field: Field, controller: GameControllerInterface): Unit = {
    val pw = new PrintWriter(new File("game.json"))
    pw.write(Json.prettyPrint(gameToJSON(field, controller)))
    pw.close()
  }

  def gameToJSON(field: Field, controller: GameControllerInterface): JsValue = {
    Json.obj(
      "currentPlayer" -> controller.currentPlayer._number,
      "field" -> Json.obj(
        "size" -> field.getScale.toString,
        "Entities" -> Json.toJson(
          for {
            y <- 0 until field.getScale
            x <- 0 until field.getScale
          } yield {
            "row" -> x
            "col" -> y
            "Entity" -> Json.toJson(field.getCell(x, y))
          }
        )
      )
    )
  }

}
