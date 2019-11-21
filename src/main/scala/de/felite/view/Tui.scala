package de.felite.view


import de.felite.controller._
import de.felite.model.{Field, Player}
import de.felite.util.{Observer, ObserverCommand, ReturnValues}

class Tui(controller: GameController) extends Observer {

  controller.add(this)

  def playerTurn(input: String): Any = {
    //input is useless?
    //where to put input reading
    input match {
      case "p" => printString(controller.field.toString)
      case "quit" => controller.gameState = ReturnValues.QUIT
      case "cancel" => controller.gameState = ReturnValues.CANCEL
      case "end" => controller.gameState = ReturnValues.END
      case "help" => printHelp()
      case _ =>
        input.toList.filter(c => c != ' ').map(c => c.toString) match {
          case xF :: yF :: action :: xT :: yT :: Nil =>
            action match {
              case "m" =>
                if (controller.doMove((xF.toInt, yF.toInt), (xT.toInt, yT.toInt)) == ReturnValues.VALID) {
                } else {
                  printString("invalid move")
                }
              case "a" =>
                if (controller.doAttack((xF.toInt, yF.toInt), (xT.toInt, yT.toInt)) == ReturnValues.VALID) {
                } else {
                  printString("invalid attack")
                }
              case _ => printString("invalid command/action")
            }
          case _ => printString("unkown command")
        }
    }
  }

  def printString(txt: String): Unit = {
    println(txt)
  }

  def printHelp(): ReturnValues.Value = {
    printString(String.format(
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
      "p", "print board",
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
    ReturnValues.VALID
  }

  override def update(observerCommand: ObserverCommand.Value): Unit = {
    if (observerCommand == ObserverCommand.PRINTSTRING) {
      printString(controller.printString)
    } else if (observerCommand == ObserverCommand.READSTRING) {
      controller.readString = scala.io.StdIn.readLine()
    } else if (observerCommand == ObserverCommand.READCOMMAND) {
      playerTurn(controller.cmdStr)
    }
  }
}
