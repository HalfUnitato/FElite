package de.felite.view


import de.felite.controller.{GameState, _}
import de.felite.controller.GameState._
import de.felite.model.{Field, Player}
import de.felite.util.{Observer, ObserverCommand, ReturnValues}
import de.felite.util.ObserverCommand._

class Tui(controller: GameController) extends Observer {

  controller.add(this)

  def playerTurn(input: String): ReturnValues.Value = {
    //input is useless?
    //where to put input reading
    input match {
      case "p" =>
        printString(controller.field.toString)
        ReturnValues.VALID
      case "quit" =>
        controller.quit
        ReturnValues.VALID
      /*case "cancel" =>
        controller.gameState = ReturnValues.CANCEL
        ReturnValues.VALID*/
      case "end" =>
        controller.end
        ReturnValues.VALID
      case "help" =>
        printHelp()
        ReturnValues.VALID
      case _ =>
        input.split(" ").toList match {
          case xF :: yF :: action :: xT :: yT :: Nil =>
            action match {
              case "m" =>
                if (controller.doMove((xF.toInt, yF.toInt), (xT.toInt, yT.toInt)) == ReturnValues.VALID) {
                  ReturnValues.VALID
                } else {
                  printString("invalid move")
                  ReturnValues.INVALID
                }
              case "a" =>
                if (controller.doAttack((xF.toInt, yF.toInt), (xT.toInt, yT.toInt)) == ReturnValues.VALID) {
                  ReturnValues.VALID
                } else {
                  printString("invalid attack")
                  ReturnValues.INVALID
                }
              case _ =>
                printString("invalid command/action")
                ReturnValues.INVALID
            }
          case _ =>
            printString("unkown command")
            ReturnValues.INVALID
        }
    }
  }

  def printString(txt: String): ReturnValues.Value = {
    println(txt)
    ReturnValues.VALID
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
    var str: String = ""

    if (controller.gameState == P1 || controller.gameState == P2) {
      str =
        if (controller.gameState == GameState.P1 || controller.gameState == GameState.P2)
          controller.getPlayerName
        else
          ""
    }
    str += GameState.message(controller.gameState)

    println(str)

    if (controller.gameState == PRINT_FIELD)
    {
      println(controller.fieldToString)
    }
    else if (observerCommand == READSTRING)
    {
      controller.readString = scala.io.StdIn.readLine()
    }
    else if (observerCommand == READCOMMAND)
    {
      val command = scala.io.StdIn.readLine()
      playerTurn(command)
    }
  }
}
