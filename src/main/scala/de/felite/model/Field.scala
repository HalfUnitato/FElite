package de.felite.model

import com.google.inject.Inject
import com.google.inject.name.Named
import de.felite.util.FileIO

import scala.util.{Failure, Success, Try}

class Field @Inject() ( @Named("DefaultSize") scal: Int) {
  var fileName:String = "src/fieldbase.txt"
  private val matrix = FileIO.readFromFile(scal,fileName)

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
          case Some(b) =>
            b match {
              case troop: Troop => base += troop.getColor
              case obs: Obstacle => base += obs.getColor
              case _ => base += Console.RESET
            }
            base += b.sign() + "\t"
            base += Console.RESET
          case None => base += Console.RESET
        }
      }

      i += 1
      base += "\n"
    }

    base
  }

  def setCell(entity: Entity, x: Int, y: Int): Boolean = {
    Try(matrix(y)(x)) match {
      case Success(v) =>
        if (entity == null)
          matrix(y)(x) = None
        else
          matrix(y)(x) = Some(entity)
      case Failure(e) => return false
    }
    true
  }

  def getCell(x: Int, y: Int): Entity = {
    matrix(y)(x) match {
      case Some(t) => t
      case None => DefEntity
    }
  }

  def getScale: Int = {
    scal
  }
}