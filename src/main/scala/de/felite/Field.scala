package de.felite

case class Field(initFile: Array[Array[String]]) { //: Array.ofDim[char]()){
  private val matrix = FileIO.readFromFile("C:\\Users\\Unitato\\Documents\\HTWG-Konstanz\\3-Semester\\SoftwareEngineering\\Tut\\FElite\\src\\fieldTest.txt")

  private def move(fromX: Int, fromY: Int, toX: Int, toY: Int): Unit = {
    matrix(toY)(toX) = matrix(fromY)(fromX)
  }

  def plausibilityCheck(fromX: Int, fromY: Int, toX: Int, toY: Int): Boolean = {
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