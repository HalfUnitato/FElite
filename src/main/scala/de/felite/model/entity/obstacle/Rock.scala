package de.felite.model.entity.obstacle

import de.felite.model.Obstacle

case class Rock(x:Int, y:Int) extends Obstacle {
  override val sign: Char = 'r'
  override val color:String = Console.CYAN
}
