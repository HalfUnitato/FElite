package de.felite.model

import de.felite.model.figure.Troop
import de.felite.model.obstacle.Obstacle
import de.felite.util.ReturnValues

import scala.collection.mutable.ListBuffer

case class Player(private val name: String = "John Doe") {
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

  def containsSoldier(soldier: Obstacle): ReturnValues.Value = {
    if (playerTroops.contains(soldier))
      return ReturnValues.VALID
    ReturnValues.INVALID
  }

  def getPlayerName: String = name

  def getUnitAmount: Int = unitAmount
}
