package de.felite.figure

import de.felite.obstacle.Branded
import javax.swing.BoundedRangeModel

case class Soldier(attV: Int, defV: Int, attR: Int, mvR: Int, healthV: Int, xPos: Int, yPos: Int) extends Troop with Branded {
  override val x: Int = xPos
  override val y: Int = yPos
  override val sign: Char = 's'
  override val health: Int = healthV
  override val attack: Int = attV
  override val defense: Int = defV
  override val attackRange: Int = attR
  override val moveRange: Int = mvR
}
