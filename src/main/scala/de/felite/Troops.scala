package de.felite


case class Troops(kind: String, attRange: Int, mvRange: Int) {
  private val kindOf = kind //Strings vs Integer?
//  private val position = Cell //x,y coordinates?
  private val attackrange = attRange //Abfrage ob größer 0? muss mind 1 sein
  private val moverange = mvRange

//  def move(from: Cell, to: Cell) {}

//  def attack(position: Cell, target: Cell) {}

  def getType: String = {
    kindOf
  }

  def getAttRange: Int = {
    attackrange
  }

  def getMvRange: Int = {
    moverange
  }

}
