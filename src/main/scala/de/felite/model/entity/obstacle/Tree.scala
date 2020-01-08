package de.felite.model.entity.obstacle

import de.felite.model.Obstacle

case class Tree(x:Int,y:Int) extends Obstacle {
  override val sign: Char = 't'
  override val color: String = Console.MAGENTA
}