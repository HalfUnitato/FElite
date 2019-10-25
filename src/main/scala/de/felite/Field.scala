package de.felite

case class Field(initFile: Array[Array[Char]]) { //: Array.ofDim[char]()){
  private val matrix = Array.ofDim[Char](20,20)

  // All Grass
  for (i <- 0 until 20) {
    for (j <- 0 until 20) {
      matrix(i)(j) = Grass.sign
    }
  }

  for (i <- 0 until 20) {
    for (j <- 0 until 20) {
      print(matrix(i)(j))
    }
    println()
  }

  /*private val field = initFile
  for (x <- initFile.indices)
    for (y <- x.)
      field(y)(x) = initFile(y)(x) match {
        case Tree.sign => Cell(Tree)
        case Grass.sign => Cell(Grass)
        case Rock.sign => Cell(Rock)
        case _ => Cell(Grass)
      }*/
}