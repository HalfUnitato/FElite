package de.felite.model.figure

import de.felite.model.Player
import de.felite.model.obstacle.Obstacle

case class Soldier(attV: Int, defV: Int, attR: Int, mvR: Int, healthV: Int, xPos: Int, yPos: Int, player: Player) extends Troop with Obstacle {
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
