package de.felite.model.entity.obstacle

case object Tree extends Obstacle {
  override val sign: Char = 't'
  override val color: String = Console.MAGENTA
}