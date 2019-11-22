package de.felite.model.entity.figure

import de.felite.model.Player
import de.felite.model.entity.Entity

trait Troop extends Entity {
  def x(): Int
  def y(): Int
  def health():Int

  def attack():Int
  def defense():Int

  def attackRange():Int
  def moveRange():Int

  def owner():Player = Player()

  def getColor:String = owner().getPlayerColor

  /*def move(from:Cell, to:Cell): Unit = {

  }*/

  //missing: position:Cell, target:Cell
//  def attackTarget(t:Troop)
}
