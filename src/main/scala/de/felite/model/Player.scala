package de.felite.model

import scala.collection.mutable.ListBuffer

case class Player(private val name: String = "John Doe", private val color: String = Console.BLACK) extends PlayerTrait {

  private val playerTroops = new ListBuffer[Troop]()

  override def addPlayerTroop(troop: Troop): Boolean = {

    if (playerTroops.contains(troop))
      return false

    playerTroops += troop
    true
  }

  override def removeTroop(troop: Troop):Boolean  = {
    if (!playerTroops.contains(troop)) {
      return  false
    }
    playerTroops.remove(playerTroops.indexOf(troop))
    true
  }
  override def clearToopList() = playerTroops.clear()

  override def containsSoldier(soldier: Entity):Boolean = {
    if(playerTroops.contains(soldier))
      true
    else
      false
  }

  override def getPlayerName: String = name

  override def getUnitAmount: Int = playerTroops.size
}
