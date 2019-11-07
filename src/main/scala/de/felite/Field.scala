package de.felite

import de.felite.figure.Troop
import de.felite.io.FileIO
import de.felite.obstacle.{Branded, Grass}

case class Field(fileName: String) { //: Array.ofDim[char]()){
  private val matrix = FileIO.readFromFile(fileName)

  // return Field
  // BUT NEVER THE ORIGINAL ONE!!!
  def getField: Array[Array[Branded]] = matrix.clone()

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

  def setSoldier(soldier: Branded, x: Int,y: Int): ReturnValues.Value = {
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