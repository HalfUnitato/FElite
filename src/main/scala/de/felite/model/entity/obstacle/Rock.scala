package de.felite.model.entity.obstacle

import de.felite.model.Obstacle

case object Rock extends Obstacle {
  override val sign: Char = 'r'
  override val color:String = Console.CYAN
}
