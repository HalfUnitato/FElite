package de.felite.controller

import de.felite.model.{Field, Player}
import de.felite.model.figure.{Archer, Soldier, Troop}
import de.felite.model.obstacle.Branded
import de.felite.util.ReturnValues
import de.felite.view.Tui

object GameControl {
  private var player1: Player = _
  private var player2: Player = _
  private val fieldTxt: Field = Field("src\\fieldTest.txt")
  private var fieldString: String = Tui.createFieldString(fieldTxt)

  def init(playerOne: Player, playerTwo: Player): Unit = {
    this.player1 = playerOne
    this.player2 = playerTwo


    setUserTroopsDefault("TopLeft", playerOne)
    setUserTroopsDefault("BottomRight", playerTwo)

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
    fieldTxt.setSoldier(soldier, x, y)

    x += 1

    player.addPlayerTroop(archer.asInstanceOf[Troop])
    fieldTxt.setSoldier(archer, x, y)

    x += 1
    player.addPlayerTroop(soldier.asInstanceOf[Troop])
    fieldTxt.setSoldier(soldier, x, y)
  }

  def getCommand(currentPlayer: Player): String = {
    Tui.readLine("type in your command: ('help' if no idea)")
  }

  def playerTurn(currentPlayer: Player, command: String): ReturnValues.Value = {
    val fieldString = Tui.createFieldString(fieldTxt)

    command match {
      case "p" => Tui.printString(fieldString)
      case "quit" => return ReturnValues.QUIT
      case "cancel" => return ReturnValues.CANCEL
      case "end" => return ReturnValues.END
      case "help" => Tui.printHelp()
      case _ =>
        command.toList.filter(c => c != ' ').map(c => c.toString) match {
          case xF :: yF :: action :: xT :: yT :: Nil =>
            action match {
              case "m" =>
                return if (doMove(currentPlayer, (xF.toInt, yF.toInt), (xT.toInt, yT.toInt)) == ReturnValues.VALID) {
                  ReturnValues.VALID
                } else {
                  Tui.printString("invalid move")
                  ReturnValues.INVALID
                }
              case "a" =>
                return if (doAttack(currentPlayer, (xF.toInt, yF.toInt), (xT.toInt, yT.toInt)) == ReturnValues.VALID) {
                  ReturnValues.VALID
                } else {
                  Tui.printString("invalid attack")
                  ReturnValues.INVALID
                }
              case _ => Tui.printString("invalid command/action") // -1 5 m 2 2 returns invalid command but should return invalid move
                return ReturnValues.INVALID
            }
          case _ => Tui.printString("invalid command")
            return ReturnValues.INVALID
        }
    }

    ReturnValues.VALID
  }

  private def doMove(currentPlayer: Player, from: (Int, Int), to: (Int, Int)): ReturnValues.Value = {
    if (currentPlayer.containsSoldier(fieldTxt.getField(from._2)(from._1)) == ReturnValues.VALID) {
      fieldTxt.doMove(from, to)
      fieldString = Tui.createFieldString(fieldTxt)
      Tui.printString(fieldString)
      return ReturnValues.VALID
    }
    ReturnValues.INVALID
  }

  private def doAttack(currentPlayer: Player, from: (Int, Int), to: (Int, Int)) = {
    // missing plausi-Check -----

    ReturnValues.VALID
  }

  def isEnd(playerOne: Player, playerTwo: Player): Boolean = {
    playerOne.getUnitAmount == 0 || playerTwo.getUnitAmount == 0
  }


}
