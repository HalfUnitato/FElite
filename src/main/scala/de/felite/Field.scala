package de.felite

case class Field(size: Int){
  private val field = Array.ofDim[Cell](size,size)
  for( x <- 0 to size)
    for(y <- 0 to size)
      field(y)(x) = Cell(Tree)
}