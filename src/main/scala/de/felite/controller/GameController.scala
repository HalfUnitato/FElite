package de.felite.controller

import de.felite.model.{Field, Player}
import de.felite.model.figure.{Archer, Soldier, Troop}
import de.felite.model.obstacle.Branded
import de.felite.util.{Observable, ReturnValues}

class GameController(var field: Field) extends Observable {
  private var player1: Player = _
  private var player2: Player = _


  def init(names: Array[String]): Unit = {
    this.player1 = Player(names(0))
    this.player2 = Player(names(1))


//    setUserTroopsDefault("TopLeft", playerOne)
//    setUserTroopsDefault("BottomRight", playerTwo)

    //match um größe des Feldes zu bestimmen?
  }

  def infoToString(): String = {
    field.toString
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

  /*def getCommand(currentPlayer: Player): String = {
    readLine("type in your command: ('help' if no idea)")
  }*/


  /*private def doMove(currentPlayer: Player, from: (Int, Int), to: (Int, Int)): ReturnValues.Value = {
    if (currentPlayer.containsSoldier(field.getField(from._2)(from._1)) == ReturnValues.VALID) {
      field.doMove(from, to)
      fieldString = Tui.createFieldString(field)
      Tui.printString(fieldString)
      return ReturnValues.VALID
    }
    ReturnValues.INVALID
  }*/

  private def doAttack(currentPlayer: Player, from: (Int, Int), to: (Int, Int)) = {
    // missing plausi-Check -----

    ReturnValues.VALID
  }

  def isEnd(playerOne: Player, playerTwo: Player): Boolean = {
    playerOne.getUnitAmount == 0 || playerTwo.getUnitAmount == 0
  }

  def getPlayerName(pos: Int):String = {
    if (pos == 1) {
      player1.getPlayerName
    } else if (pos == 2) {
      player2.getPlayerName
    } else {
      "IllegalIndex"
    }
  }
}
