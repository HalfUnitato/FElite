package de.felite

case class Field(initFile: Array[Array[Char]]) { //: Array.ofDim[char]()){
  private val field = initFile
  for (x <- initFile.indices)
    for (y <- x.)
      field(y)(x) = initFile(y)(x) match {
        case Tree.sign => Cell(Tree)
        case Grass.sign => Cell(Grass)
        case Rock.sign => Cell(Rock)
        case _ => Cell(Grass)
      }
}