package de.felite.controller

import de.felite.model.{Field, Player}
import de.felite.model.figure.{Archer, Soldier, Troop}
import de.felite.model.obstacle.Branded
import de.felite.util.{Observable, ObserverCommand, ReturnValues}

class GameController(var field: Field) extends Observable {
  private var player1: Player = _
  private var player2: Player = _
  var currentPlayer: Player = _
  var printString: String = _
  var readString: String = _
  var gameState: ReturnValues.Value = ReturnValues.VALID

  def init(): Unit = {
    println("init Controller")
    printString = "Name of player number one:"
    notifyObservers(ObserverCommand.PRINTSTRING)
    notifyObservers((ObserverCommand.READSTRING))
    this.player1 = Player(readString)
    printString = "Name of player number two:"
    notifyObservers(ObserverCommand.PRINTSTRING)
    notifyObservers((ObserverCommand.READSTRING))
    this.player2 = Player(readString)

    currentPlayer = player1

    printString = "------ " + currentPlayer.getPlayerName + "\'s turn ------"
    notifyObservers(ObserverCommand.PRINTSTRING)

    setUserTroopsDefault("TopLeft", player1)
    setUserTroopsDefault("BottomRight", player2)

    //match um größe des Feldes zu bestimmen?
  }
  def switchPlayer(): Unit ={
    printString = "------ " + currentPlayer.getPlayerName + "\'s turn ------"
    notifyObservers(ObserverCommand.PRINTSTRING)
    currentPlayer =
      if (currentPlayer.equals(player1))
        player2
      else
        player1
  }
  private def setUserTroopsDefault(pos: String, player: Player): Unit = {
    var y = 0
    var x = 0

    if (pos.equals("TopLeft")) {
      y = 0
      x = 0
    }
    else if (pos.equals("BottomRight")) {
      y = 5
      x = 3
    }

    val soldier: Branded = Soldier(3, 6, 1, 4, 6, x, y)
    val archer = Archer(2, 3, 4, 2, 3, x, y)
    player.addPlayerTroop(soldier.asInstanceOf[Troop])
    field.setSoldier(soldier, x, y)

    x += 1

    player.addPlayerTroop(archer.asInstanceOf[Troop])
    field.setSoldier(archer, x, y)

    x += 1
    player.addPlayerTroop(soldier.asInstanceOf[Troop])
    field.setSoldier(soldier, x, y)
  }

  /* def getCommand(currentPlayer: Player): String = {
     printString = "type in your command: ('help' if no idea)"
     notifyObservers(ObserverCommand.PRINTSTRING)
   }*/


  def doMove(from: (Int, Int), to: (Int, Int)): ReturnValues.Value = {
    if (currentPlayer.containsSoldier(field.getField(from._2)(from._1)) == ReturnValues.VALID) {
      field.doMove(from, to)
      printString = field.toString
      notifyObservers(ObserverCommand.PRINTSTRING)
      return ReturnValues.VALID
    }
    ReturnValues.INVALID
  }

  def doAttack(from: (Int, Int), to: (Int, Int)): ReturnValues.Value = {
    // missing plausi-Check -----

    ReturnValues.VALID
  }

  def isEnd: Boolean =
    player1.getUnitAmount == 0 || player2.getUnitAmount == 0

  def getPlayerName(pos: Int): String = {
    if (pos == 1) {
      player1.getPlayerName
    } else if (pos == 2) {
      player2.getPlayerName
    } else {
      "IllegalIndex"
    }
  }

  def nextPlayerMove(): Unit = {
    notifyObservers(ObserverCommand.READCOMMAND)
  }
}
