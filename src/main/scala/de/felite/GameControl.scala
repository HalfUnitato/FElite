package de.felite

import de.felite.io.Tui

object GameControl {
  private var field: Field = _

  def init(): Unit = {
    field = Field("C:\\Users\\Unitato\\Documents\\HTWG-Konstanz" +
      "\\3-Semester\\SoftwareEngineering" +
      "\\Tut\\FElite\\src\\fieldTest.txt")
    Tui.printField(field)
  }

  def playerTurn(currentPlayer: Player): ReturnValues.Value = {
    val command = Tui.readLine("type in your command: {--h for help}")
    command match {
      case "p" => Tui.printField(field)
      case "quit" => return ReturnValues.QUIT
      case "cancel" => return ReturnValues.CANCEL
      case "end" => return ReturnValues.END
      case "--h" => printHelp()
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
              case "a" => return if (doAttack(currentPlayer, (xF.toInt, yF.toInt), (xT.toInt, yT.toInt)) == ReturnValues.VALID) {
                ReturnValues.VALID
              } else {
                Tui.printString("invalid attack")
                ReturnValues.INVALID
              }
              case _ => Tui.printString("invalid command")
                return ReturnValues.INVALID
            }
          case _ => Tui.printString("invalid command")
            return ReturnValues.INVALID
        }
    }

    ReturnValues.VALID
  }

  private def doMove(currentPlayer: Player, from: (Int, Int), to: (Int, Int)) = {
    // missing plausi-Check -----
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
      "%s:%20s\n" +
        "%s:%20s\n" +
        "%s:%20s\n" +
        "%s:%20s\n" +
        "%s:\n" +
        "%10s:%20s\n" +
        "%10s:%20s\n" +
        "%15s:%20s\n" +
        "%15s:%20s\n" +
        "%10s:%20s\n",
      "p", "print board",
      "quit", "end game",
      "cancel", "undo last command",
      "end", "end turn",
      "xF yF c xT yT",
      "xF yF", " coordinates from",
      "c", "action",
      "m", "move",
      "a", "attack",
      "xT yT", "coordinates to"))
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