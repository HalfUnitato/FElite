package de.felite.obstacle

case object Rock extends Obstacle(false) with Branded {

  override val sign: Char = 'r'
}
