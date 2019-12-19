package de.felite.model

import de.felite.model.entity.figure.{BuildArcher, BuildSolider}
import de.felite.model.entity.obstacle.{Obstacle, Rock, Tree}

trait ModelInterface {}

trait Entity {
  def sign(): Char

  def getColor: String
}

trait PlayerTrait {
  this: Player =>

  def addPlayerTroop(troop: Troop): Boolean

  def removeTroop(troop: Troop): Boolean

  def containsSoldier(soldier: Entity): Boolean

  def getPlayerName: String

  def getPlayerColor: String

  def getUnitAmount: Int
}

trait Troop extends Entity {
  def x(): Int

  def y(): Int

  def health(): Int

  def attack(): Int

  def defense(): Int

  def attackRange(): Int

  def moveRange(): Int

  def owner(): Player = Player()

  override def getColor: String = owner().getPlayerColor

}

// Std Grass
case object DefEntity extends Obstacle {
  val sign: Char = 'g'
  override val walkthrough: Boolean = true
  override val color: String = Console.GREEN
}

object SoldierFactory {
  def create(typ: Char, pos: (Int, Int), healt: Int, player: Player): Troop = {
    if (typ.equals('a'))
      BuildArcher.buildArcher(pos._1, pos._1, player, healt)
    else
      BuildSolider.buldSoldier(pos._1, pos._1, player, healt)
  }
}

object ObstacleFactory {
  def create(typ: Char): Obstacle = {
    typ match
    {
      case 'r' => Rock
      case 'g' => DefEntity
      case 't' => Tree
    }
  }
}