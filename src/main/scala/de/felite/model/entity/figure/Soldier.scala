package de.felite.model.entity.figure

import de.felite.model.{Player, Troop}

case class Soldier(attV: Int, defV: Int, attR: Int, mvR: Int, healthV: Int, xPos: Int, yPos: Int, player: Player) extends Troop {
  override val x: Int = xPos
  override val y: Int = yPos
  override val sign: Char = 's'
  override val health: Int = healthV
  override val attack: Int = attV
  override val defense: Int = defV
  override val attackRange: Int = attR
  override val moveRange: Int = mvR
  override val owner: Player = player

}
object BuildSolider{
  def buldSoldier(x:Int, y:Int, player : Player, health:Int = 6): Soldier = Soldier(3, 6, 1, 4, health, x, y, player)
}
