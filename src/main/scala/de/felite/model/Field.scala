package de.felite.model

import de.felite.model.entity.Entity
import de.felite.model.entity.figure.Troop
import de.felite.model.entity.obstacle.{Grass, Obstacle, Rock}
import de.felite.util.{FileIO, ReturnValues}

import scala.collection.mutable.ListBuffer
import scala.util.{Failure, Success, Try}

object Field {
  private val scal: Int = 3
  private val fileName = FileIO.setScal(scal)
  private val matrix = FileIO.readFromFile()

  // return Field
  // BUT NEVER THE ORIGINAL ONE!!!
  def getField: Array[Array[Option[Entity]]] = matrix.clone()

  override def toString: String = {

    var base = ""
    for (i <- 0 until FileIO.getScal)
      base += "\t" + i
    base += "\n"
    var i = 0
    for (y <- matrix) {
      base += i + "\t"
      for (x <- y) {
        x match {
          case Some(b) => base += b.sign()
          case None => base += Console.RESET
        }
        base += "\t"
        base += Console.RESET
      }
      i += 1
      base += "\n"
    }
    base
  }

  def setCell(entity: Entity, x: Int, y: Int): ReturnValues.Value = {
    Try(matrix(y)(x)) match {
      case Success(v) => matrix(y)(x) = Some(entity)
      case Failure(e) => return ReturnValues.INVALID
    }
    ReturnValues.VALID
  }

  def getCell(x: Int, y: Int) = {
    matrix(y)(x) match {
      case Some(t) => t
      case None => Grass
    }
  }

  def getScal: Int = {
    scal
  }
}