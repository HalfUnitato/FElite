package de.felite.model.entity.figure

import de.felite.model.Player
import de.felite.model.entity.obstacle.Obstacle

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
  def buldSoldier(x:Int, y:Int, player : Player) = Soldier(3, 6, 1, 4, 6, x, y, player)
}
