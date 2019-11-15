package de.felite.model.entity.obstacle

case object Rock extends Obstacle {
  override val sign: Char = 'r'
  override val color:String = Console.CYAN
}
