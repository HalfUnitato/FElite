package de.felite

import de.felite.io.FileIO
import de.felite.obstacle.Grass

case class Field(fileName: String) { //: Array.ofDim[char]()){
  private val matrix = FileIO.readFromFile(fileName)

  // return Field
  // BUT NEVER THE ORIGINAL ONE!!!
  def getField: Array[Array[Char]] = matrix.clone()

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
    matrix(from._2)(from._1) = 'g'
    ReturnValues.VALID
  }

}