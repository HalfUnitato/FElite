package de.felite.model.entity.obstacle


case object Grass extends Obstacle {
  override val sign: Char = 'g'
  override val walkthrough: Boolean = true
  override val color: String = Console.GREEN
}
