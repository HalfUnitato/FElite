package de.felite

import de.felite.figure.Troop

import scala.collection.mutable.ListBuffer

case class Player(name: String = "John Doe") {
  private var playerTroops = new ListBuffer[Troop]()
  private val playerName = name
  private var unitAmount = 42 //UnitList.length

  def addPlayerTroop(troop: Troop): Unit = {
    playerTroops += troop
  }

  def removeTroop(troop: Troop): ReturnValues.Value = {
    if (!playerTroops.contains(troop)) {
      ReturnValues.INVALID
    }
    playerTroops.remove(playerTroops.indexOf(troop))
    ReturnValues.VALID

  }

  def getPlayerName: String = playerName

  def getUnitAmount: Int = unitAmount
}
