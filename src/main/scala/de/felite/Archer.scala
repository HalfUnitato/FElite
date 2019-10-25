package de.felite

case class Archer(att:Int,mv:Int) extends Troop {
  override val attackRange:Int = att
  override val moveRange:Int = mv


}
