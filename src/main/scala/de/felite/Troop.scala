package de.felite

trait Troop {

  def health():Int = 0

  def attack():Int = 0
  def defense():Int = 0

  def attackRange():Int = 0
  def moveRange():Int = 0

  /*def move(from:Cell, to:Cell): Unit = {

  }*/

  //missing: position:Cell, target:Cell
//  def attackTarget(t:Troop)
}
