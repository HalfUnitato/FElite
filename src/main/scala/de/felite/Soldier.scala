package de.felite

case class Soldier(att:Int,mv:Int) extends Troop {
  override val attackRange: Int = att
  override val moveRange: Int = mv
}
