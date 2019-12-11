package de.felite.model

import de.felite.model.entity.Entity
import de.felite.model.entity.figure.Troop
import de.felite.model.entity.obstacle.Obstacle
import de.felite.util.PackTroops

import scala.collection.mutable.ListBuffer

case class Player(private val name: String = "John Doe", private val color: String = Console.BLACK) {

  private var playerTroops = new ListBuffer[Troop]()
  private var unitAmount = 42 //UnitList.length

  def addPlayerTroop(troop: Troop): Boolean = {

    if (playerTroops.contains(troop))
      return false

    playerTroops += troop
    true
  }

  def removeTroop(troop: Troop):Boolean  = {
    if (!playerTroops.contains(troop)) {
     return  false
    }
    playerTroops.remove(playerTroops.indexOf(troop))
    true
  }

  def containsSoldier(soldier: Entity):Boolean = {
    if(playerTroops.contains(soldier))
      true
    else
      false
  }

  def getPlayerName: String = name

  def getPlayerColor: String = color

  def getUnitAmount: Int = unitAmount
}
