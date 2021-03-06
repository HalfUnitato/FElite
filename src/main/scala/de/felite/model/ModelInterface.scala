package de.felite.model

import de.felite.model.entity.figure.BuildFootPatrol
import de.felite.model.entity.obstacle.SimpleObstacle

import scala.xml.Elem

trait ModelInterface {}

trait Entity {
  def walkThrough: Boolean = false

  def sign: Char

  def getColor: String

  def toXML(x: Int, y: Int): Elem

}


trait PlayerTrait {
  this: Player =>
  def number(): Int

  def colour: String

  def addPlayerTroop(troop: Troop): Boolean

  def removeTroop(troop: Troop): Boolean

  def clearToopList(): Unit

  def containsSoldier(soldier: Entity): Boolean

  def getPlayerName: String

  def getUnitAmount: Int
}

trait Troop extends Entity {

  def health(): Int

  def attack(): Int

  def defense(): Int

  def attackRange(): Int

  def moveRange(): Int

  def owner(): Player

  override def toXML(x: Int, y: Int): Elem = {
    <troop row={y.toString} col={x.toString} health={health().toString} player={owner().number.toString}>
      {sign}
    </troop>
  }

}

object SoldierFactory {
  def apply(typ: Char, health: Int = -1, player: Player): Troop = {
    if (health == -1) {
      BuildFootPatrol.buildFootPatrol(typ, player, colour = player.colour)
    } else {
      BuildFootPatrol.buildFootPatrol(typ, player, health, player.colour)
    }
  }
}

trait Obstacle extends Entity {

  def color(): String

  override def getColor: String = color()

  override def toXML(x: Int, y: Int): Elem = {
    <obstacle row={y.toString} col={x.toString}>
      {sign}
    </obstacle>
  }
}


object ObstacleFactory {
  def apply(typ: Char): Obstacle = {
    val bool: Boolean = if (typ == 'g') true else false
    SimpleObstacle(typ, bool)
  }
}
