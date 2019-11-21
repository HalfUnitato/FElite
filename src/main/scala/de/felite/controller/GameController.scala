package de.felite.controller

import de.felite.model.{Field, Player}
import de.felite.model.entity.figure.{Archer, Soldier, Troop}
import de.felite.util.{Observable, ObserverCommand, ReturnValues}

class GameController(var field: Field) extends Observable {
  private var player1: Player = _
  private var player2: Player = _
  var currentPlayer: Player = _
  var printString: String = _
  var readString: String = _
  var cmdStr: String =_
  var gameState: ReturnValues.Value = ReturnValues.VALID

  def init(): Unit = {
    println("------ Start of Initialisation ------")
    println("initialize Controller")

    printString = "Name of player number one:"
    notifyObservers(ObserverCommand.PRINTSTRING)
    notifyObservers(ObserverCommand.READSTRING)
    this.player1 = Player(readString, Console.BLUE)

    printString = "Name of player number two:"
    notifyObservers(ObserverCommand.PRINTSTRING)
    notifyObservers(ObserverCommand.READSTRING)
    this.player2 = Player(readString, Console.RED)

    setUserTroopsDefault("TopLeft", player1)
    setUserTroopsDefault("BottomRight", player2)

    currentPlayer = player1


    println("------ End of Initialisation ------")

    printString = field.toString
    notifyObservers(ObserverCommand.PRINTSTRING)

    printString = "------ " + currentPlayer.getPlayerName + "\'s turn ------\n"
    printString += "type in your command: ('help' if no idea)"
    notifyObservers(ObserverCommand.PRINTSTRING)
  }

  def switchPlayer(): Unit = {
    currentPlayer =
      if (currentPlayer.equals(player1))
        player2
      else
        player1
    printString = "------ " + currentPlayer.getPlayerName + "\'s turn ------\n"
    printString = "type in your command: ('help' if no idea)"
    notifyObservers(ObserverCommand.PRINTSTRING)
    printString = field.toString
    notifyObservers(ObserverCommand.PRINTSTRING)
  }

  private def setUserTroopsDefault(pos: String, player: Player): Unit = {
    var y = 0
    var x = 0

    if (pos.equals("TopLeft")) {
      y = 0
      x = 0
    }
    else if (pos.equals("BottomRight")) {
      y = field.getScal - 1
      x = field.getScal - 2
    }

    val soldier = Soldier(3, 6, 1, 4, 6, x, y, player)
    val archer = Archer(2, 3, 4, 2, 3, x, y, player)
    player.addPlayerTroop(soldier.asInstanceOf[Troop])
    field.setSoldier(soldier, x, y)

    x += 1

    player.addPlayerTroop(archer.asInstanceOf[Troop])
    field.setSoldier(archer, x, y)
  }


  def doMove(from: (Int, Int), to: (Int, Int)): ReturnValues.Value = {
    val tmpScal = field.getScal

    //check if outOBounds
    if (from._1 >= tmpScal || from._2 >= tmpScal || to._1 >= tmpScal || to._2 >= tmpScal
          || from._1 < 0 || from._2 < 0 || to._1 < 0 || to._2 < 0) {
      return ReturnValues.INVALID
    }

    //check if currentPlayer owns Soldier specified at from
    if (currentPlayer.containsSoldier(field.getField(from._2)(from._1)) == ReturnValues.VALID) {
      field.doMove(from, to)
      printString = field.toString
      notifyObservers(ObserverCommand.PRINTSTRING)
      return ReturnValues.VALID
    }

    //else return invalid
    ReturnValues.INVALID
  }

  def doAttack(from: (Int, Int), to: (Int, Int)): ReturnValues.Value = {
    // missing plausi-Check -----

    ReturnValues.VALID
  }

  def isEnd: Boolean =
    player1.getUnitAmount == 0 || player2.getUnitAmount == 0

  def getPlayerName: String =
    currentPlayer.getPlayerName

  def nextPlayerMove(str:String): Unit = {
    cmdStr = str
    notifyObservers(ObserverCommand.READCOMMAND)
  }
}
