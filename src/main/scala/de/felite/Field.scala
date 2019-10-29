package de.felite

case class Field(fileName:String) { //: Array.ofDim[char]()){
  private val matrix = FileIO.readFromFile(fileName)

  private def move(fromX: Int, fromY: Int, toX: Int, toY: Int): Unit = {
    matrix(toY)(toX) = matrix(fromY)(fromX)
  }

  def doMove(fromX: Int, fromY: Int, toX: Int, toY: Int): Boolean = {
    // Indexierung grueltig? keine IndexOutOfRange Exception
    try {
      matrix(fromY)(fromX)
      matrix(toY)(toX)
    }
    catch {
      case _: Throwable => return false
    }
    move(fromX, fromY, toX, toY)
    true
  }

}