package de.felite.model

import de.felite.model.entity.Entity
import de.felite.model.entity.figure.Troop
import de.felite.model.entity.obstacle.Obstacle
import de.felite.util.ReturnValues

import scala.collection.mutable.ListBuffer

case class Player(private val name: String = "John Doe", private val color: String = Console.BLACK) {
  private var playerTroops = new ListBuffer[Troop]()
  private var unitAmount = 42 //UnitList.length

  def addPlayerTroop(troop: Troop): ReturnValues.Value = {
    if (playerTroops.contains(troop))
      return ReturnValues.INVALID

    playerTroops += troop
    ReturnValues.VALID
  }

  def removeTroop(troop: Troop): ReturnValues.Value = {
    if (!playerTroops.contains(troop)) {
      return ReturnValues.INVALID
    }
    playerTroops.remove(playerTroops.indexOf(troop))
    ReturnValues.VALID
  }

  def containsSoldier(soldier: Entity): ReturnValues.Value = {
    if (playerTroops.contains(soldier))
      return ReturnValues.VALID
    ReturnValues.INVALID
  }

  def getPlayerName: String = name

  def getPlayerColor: String = color

  def getUnitAmount: Int = unitAmount
}
