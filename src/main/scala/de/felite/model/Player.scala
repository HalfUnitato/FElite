package de.felite.model

import de.felite.model.figure.Troop
import de.felite.model.obstacle.Branded
import de.felite.util.ReturnValues

import scala.collection.mutable.ListBuffer

case class Player(name: String = "John Doe") {
  private var playerTroops = new ListBuffer[Troop]()
  private val playerName = name
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

  def containsSoldier(soldier: Branded): ReturnValues.Value = {
    if (playerTroops.contains(soldier))
      return ReturnValues.VALID
    ReturnValues.INVALID
  }

  def getPlayerName: String = playerName

  def getUnitAmount: Int = unitAmount
}
