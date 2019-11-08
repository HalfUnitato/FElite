package de.felite.model.obstacle

case object Tree extends Obstacle(false) with Branded {
  override val sign: Char = 't'
}