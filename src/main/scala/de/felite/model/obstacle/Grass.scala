package de.felite.model.obstacle

case object Grass extends Obstacle(true) with Branded {
  override val sign: Char = 'g'
}
