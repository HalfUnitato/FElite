package de.felite

case class Player(name: String = "John Doe") {
  //UnitListe
  private val playerName = name
  private var unitAmount = 42 //UnitList.length

  def getPlayerName: String = playerName

  def getUnitAmount: Int = unitAmount
}
