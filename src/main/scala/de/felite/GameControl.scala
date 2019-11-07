package de.felite

import de.felite.figure.{Archer, Soldier}
import de.felite.io.Tui

object GameControl {
  private var field: Field = _
  private var player1: Player = _
  private var player2: Player = _

  def init(playerOne: Player, playerTwo: Player): Unit = {
    this.player1 = playerOne
    this.player2 = playerTwo


    field = Field("src\\fieldTest.txt")
    Tui.printField(field)

    setUserTroopsDefault("TopLeft", playerOne)
    setUserTroopsDefault("BottomRight", playerTwo)
  }

  private def setUserTroopsDefault(pos: String, player: Player): Unit = {
    if (pos.equals("TopLeft")) {
      val y = 0
      player.addPlayerTroop(new Soldier(3, 6, 1, 4, 6, 0, y))
      player.addPlayerTroop(new Archer(2, 3, 4, 2, 3, 1, y))
      player.addPlayerTroop(new Soldier(3, 6, 1, 4, 6, 2, y))
    } else if(pos.equals("BottomRight")) {
      val y = 5
      player.addPlayerTroop(new Soldier(3, 6, 1, 4, 6, 0, y))
      player.addPlayerTroop(new Archer(2, 3, 4, 2, 3, 1, y))
      player.addPlayerTroop(new Soldier(3, 6, 1, 4, 6, 2, y))
    }
  }

  def playerTurn(currentPlayer: Player): ReturnValues.Value = {
    val command = Tui.readLine("type in your command: ('help' if no idea)")
    command match {
      case "p" => Tui.printField(field)
      case "quit" => return ReturnValues.QUIT
      case "cancel" => return ReturnValues.CANCEL
      case "end" => return ReturnValues.END
      case "help" => printHelp()
      case _ =>
        command.toList.filter(c => c != ' ').map(c => c.toString) match {
          case xF :: yF :: action :: xT :: yT :: Nil =>
            action match {
              case "m" =>
                return if (doMove(currentPlayer, (xF.toInt, yF.toInt), (xT.toInt, yT.toInt)) == ReturnValues.VALID) {
                  Tui.printField(field)
                  ReturnValues.VALID
                } else {
                  Tui.printString("invalid move")
                  ReturnValues.INVALID
                }
              case "a" => return if (doAttack(currentPlayer, (xF.toInt, yF.toInt), (xT.toInt, yT.toInt)) == ReturnValues.VALID) {
                Tui.printField(field)
                ReturnValues.VALID
              } else {
                Tui.printString("invalid attack")
                ReturnValues.INVALID
              }
              case _ => Tui.printString("invalid command") // -1 5 m 2 2 returns invalid command but should return invalid move
                return ReturnValues.INVALID
            }
          case _ => Tui.printString("invalid command")
            return ReturnValues.INVALID
        }
    }

    ReturnValues.VALID
  }

  private def doMove(currentPlayer: Player, from: (Int, Int), to: (Int, Int)) = {
    //if()
    field.doMove(from, to)
  }

  private def doAttack(currentPlayer: Player, from: (Int, Int), to: (Int, Int)) = {
    // missing plausi-Check -----

    ReturnValues.VALID;
  }

  def isEnd(playerOne: Player, playerTwo: Player) = {
    playerOne.getUnitAmount == 0 || playerTwo.getUnitAmount == 0
  }

  private def printHelp(): Unit = {
    Tui.printString(String.format(
      "%s:%28s\n" +
        "%s:%22s\n" +
        "%s:%29s\n" +
        "%s:%23s\n" +
        "%s:\n" +
        "%s:%25s\n" +
        "%7s:%21s\n" +
        "%13s:%15s\n" +
        "%20s:\n" +
        "%10s:%8s\n" +
        "%10s:%10s\n",
      "p", "print board", //coordinates of the board? print them too?
      "quit", "end game",
      "cancel", "undo last command",
      "end", "end turn",
      "xF yF c xT yT where",
      "xF yF", "coordinates from",
      "c", "command/action",
      "xT yT", "coordinates to",
      "c being one of",
      "m", "to move",
      "a", "to attack"))
    /*"quit:\t\t\tend game\n" +
      "cancel:\t\t\tundo last command\n" +
      "end:\t\t\t\tend turn\n" +
      ":\n" +
      "\t\t\t\txF yF: coordinates from\n" +
      "\t\t\t\tc: action\n" +
      "\t\t\t\t\tm: move\n" +
      "\t\t\t\t\ta: attack\n" +
      "\t\t\t\txT yT: coordinates to\n")*/
  }
}