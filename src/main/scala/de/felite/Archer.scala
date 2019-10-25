package de.felite

case class Archer(attV: Int, defV: Int, attR: Int, mvR: Int) extends Troop {

  override val attack: Int = attV
  override val defense: Int = defV
  override val attackRange: Int = attR
  override val moveRange: Int = mvR


}
