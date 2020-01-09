package de.felite.model.entity.figure

import de.felite.model.{Player, Troop}

import scala.xml.Elem

case class FootPatrol(sign: Char, attV: Int, defV: Int, attR: Int, mvR: Int, healthV: Int, x: Int, y: Int, player: Player, colour: String, _walkthrough: Boolean) extends Troop {
  override val walkThrough: Boolean = _walkthrough
  override val health: Int = healthV
  override val attack: Int = attV
  override val defense: Int = defV
  override val attackRange: Int = attR
  override val moveRange: Int = mvR
  override val owner: Player = player

  override def toXML: Elem = {
    <troop row={y.toString} col={x.toString} health={health.toString} player={owner.number.toString}>
      {sign}
    </troop>
  }

  override def getColor: String = colour
}

object BuildFootPatrol {
  def buildFootPatrol(typ: Char, x: Int, y: Int, player: Player, health: Int = -1, colour: String): FootPatrol = {
   var _health = health
    if (typ == 's') {
      if (health == -1) _health = 6
      FootPatrol(typ, 2, 4, 1, 4, _health, x, y, player, colour, _walkthrough = true)
    }
    else { // 'a'
      if (health == -1) _health = 3
      FootPatrol(typ, 1, 2, 4, 2, _health, x, y, player, colour, _walkthrough = true)
    }
  }
}