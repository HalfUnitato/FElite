package de.felite.controller

import GameState._
import de.felite.model.{Field, Player}
import de.felite.model.entity.figure.{Archer, Soldier, Troop}
import de.felite.util.{Observable, ObserverCommand, ReturnValues}
import de.felite.util.ReturnValues._
import de.felite.util.ObserverCommand._

class GameController(var field: Field) extends Observable {
  var gameState: GameState = INIT
  private var currentPlayer: Player = _
  private var player1: Player = _
  private var player2: Player = _
  var printString: String = _
  var readString: String = _
  var cmdStr: String = _

  def quit: Unit = {
    // quit Game
    gameState = QUIT
    notifyObservers(ObserverCommand.PRINTSTRING)
  }

  def end: Unit = {
    // end Turn
    gameState = END
    notifyObservers(ObserverCommand.PRINTSTRING)
    switchPlayer()
  }

  def init(testflag: Int = 0): Unit = {
    println("------ Start of Initialisation ------")

    if (testflag == 0) {
      gameState = P1_INI
      notifyObservers(ObserverCommand.READSTRING)
      this.player1 = Player(readString, Console.BLUE)

      gameState = P2_INI
      notifyObservers(ObserverCommand.READSTRING)
      this.player2 = Player(readString, Console.RED)
    } else {
      this.player1 = Player()
      this.player2 = Player("Hans Peter")
    }

    setUserTroopsDefault("TopLeft", player1)
    setUserTroopsDefault("BottomRight", player2)

    gameState = P1
    currentPlayer = player1

    println("------ End of Initialisation ------")

    gameState = PRINT_FIELD
    notifyObservers(PRINTSTRING)

    gameState = P1
  }

  def switchPlayer(): Unit = {
    gameState =
      if (currentPlayer == player1)
        P2
      else
        P1
    currentPlayer =
      if (currentPlayer == player1)
        player2
      else
        player1

    notifyObservers(PRINTSTRING)
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

  def fieldToString = field.toString

  def doMove(from: (Int, Int), to: (Int, Int)): ReturnValues.Value = {
    val tmpScal = field.getScal

    //check if outOBounds
    if (from._1 >= tmpScal || from._2 >= tmpScal || to._1 >= tmpScal || to._2 >= tmpScal
      || from._1 < 0 || from._2 < 0 || to._1 < 0 || to._2 < 0) {
      return INVALID
    }

    //check if currentPlayer owns Soldier specified at from
    if (currentPlayer.containsSoldier(field.getField(from._2)(from._1)) == ReturnValues.VALID) {
      field.doMove(from, to)
      gameState = PRINT_FIELD
      notifyObservers((ObserverCommand.PRINTSTRING))
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
    (if (gameState == GameState.P1) player1 else player2).getPlayerName

  def nextPlayerMove(): Unit = {
    gameState = NEXT_CMD
    notifyObservers(ObserverCommand.READCOMMAND)
  }
}
