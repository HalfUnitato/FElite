package de.felite.model.entity.figure

import de.felite.model.Player
import de.felite.model.entity.Entity

trait Troop extends Entity {
  def x(): Int = 0
  def y(): Int = 0
  def health():Int = 0

  def attack():Int = 0
  def defense():Int = 0

  def attackRange():Int = 0
  def moveRange():Int = 0

  def owner():Player = Player()

  def getColor:String = owner().getPlayerColor

  /*def move(from:Cell, to:Cell): Unit = {

  }*/

  //missing: position:Cell, target:Cell
//  def attackTarget(t:Troop)
}
