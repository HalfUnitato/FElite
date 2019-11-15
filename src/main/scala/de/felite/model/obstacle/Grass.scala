package de.felite.model.obstacle

case object Grass extends Obstacle {
  override val sign: Char = 'g'
  override val walkthrough: Boolean = true
}
