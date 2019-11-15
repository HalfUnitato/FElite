package de.felite.model

import de.felite.controller.GameController
import de.felite.model.figure.Troop
import de.felite.model.obstacle.{Grass, Obstacle}
import de.felite.util.{FileIO, ReturnValues}

case class Field(fileName: String) { //: Array.ofDim[char]()){
  val scal: Int = 3
  FileIO.scal = scal
  private val matrix = FileIO.readFromFile(fileName)

  // return Field
  // BUT NEVER THE ORIGINAL ONE!!!
  def getField: Array[Array[Obstacle]] = matrix.clone()

  override def toString(): String = {
    var base = ""
    //variable amount of numbers relative to size of field #skalierung
    for (i <- 0 until scal)
      base += "\t" + i
    base += "\n"
    var i = 0
    for (y <- matrix) {
      base += i + "\t"
      for (x <- y) {
        x match {
          case troop: Troop => base += troop.owner().color
          case _ => Console.RESET
        }
        base += x.sign() + "\t"
      }
      i += 1
      base += "\n"
    }
    base
  }

  def doMove(from: (Int, Int), to: (Int, Int)): ReturnValues.Value = {
    // Indexierung grueltig? keine IndexOutOfRange Exception
    try {
      // teste IndexZugriffe
      matrix(from._2)(from._1)
      matrix(to._2)(to._1)
    }
    catch {
      case _: Throwable => return ReturnValues.INVALID
    }
    matrix(to._2)(to._1) = matrix(from._2)(from._1)
    matrix(from._2)(from._1) = Grass
    ReturnValues.VALID
  }

  def setSoldier(soldier: Obstacle, x: Int, y: Int): ReturnValues.Value = {
    try {
      // teste IndexZugriffe
      matrix(y)(x)
    }
    catch {
      case _: Throwable => return ReturnValues.INVALID
    }
    matrix(y)(x) = soldier
    ReturnValues.VALID
  }
}
