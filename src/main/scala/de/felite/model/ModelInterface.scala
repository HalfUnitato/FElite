package de.felite.model

import de.felite.model.entity.figure.{BuildArcher, BuildSolider}
import de.felite.model.entity.obstacle.{Rock, Tree}

import scala.xml.Elem

trait ModelInterface {}

trait Entity {
  def x(): Int
  def y(): Int
  def sign(): Char
  def getColor: String
  def toXML: Elem
}

trait PlayerTrait {
  this: Player =>

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

  def owner(): Player = Player()

  override def toXML: Elem = {
    <troop row={y().toString} col={x().toString} health={health()} player={owner()}>
      {sign()}
    </troop>
  }
}

// Std Grass
case class DefEntity(x:Int,y:Int) extends Obstacle {
  val sign: Char = 'g'
  override val walkthrough: Boolean = true
  override val color: String = Console.GREEN
}

object SoldierFactory {
  def create(typ: Char, pos: (Int, Int), health: Int = -1, player: Player): Troop = {
    if (health == -1) {
      if (typ.equals('a'))
        BuildArcher.buildArcher(pos._1, pos._1, player)
      else
        BuildSolider.buldSoldier(pos._1, pos._1, player)
    } else {
      if (typ.equals('a'))
        BuildArcher.buildArcher(pos._1, pos._1, player, health)
      else
        BuildSolider.buldSoldier(pos._1, pos._1, player, health)
    }
  }
}

trait Obstacle extends Entity {

  def walkthrough(): Boolean = false

  def color(): String

  def toXML: Elem = {
    <obstacle row={y().toString} col={x().toString}>
      {sign()}
    </obstacle>
  }

  override def getColor: String = color()
}

object ObstacleFactory {
  def create(typ: Char,x:Int, y:Int): Obstacle = {
    typ match {
      case 'r' => Rock(x,y)
      case 'g' => DefEntity(x,y)
      case 't' => Tree(x,y)
    }
  }
}