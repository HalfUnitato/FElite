package de.felite.view


import de.felite.controller.GameController
import de.felite.controller.status._
import de.felite.util.{Observer, ObserverCommand, ReturnValues}
import de.felite.util.ObserverCommand._

import scala.util.{Failure, Success, Try}

class Tui(controller: GameController) extends Observer {
  controller.add(this)

  def playerTurn(input: String): ReturnValues.Value = {
    //input is useless?
    //where to put input reading
    input match {
      case "undo" =>
        controller.undo
        ReturnValues.VALID
      case "redo" =>
        controller.redo
        ReturnValues.VALID
      case "p" =>
        printString(controller.FieldToString)
        ReturnValues.VALID
      case "quit" =>
        State.gameState = new QuitState(controller)
        State.gameState.handle
        ReturnValues.VALID
      /*case "cancel" =>
        controller.gameState = ReturnValues.CANCEL
        ReturnValues.VALID*/
      case "end" =>
        State.gameState = new EndState(controller)
        State.gameState.handle
        ReturnValues.VALID
      case "help" =>
        printHelp()
        ReturnValues.VALID
      case _ =>
        input.split(" ").toList match {
          case xF :: yF :: action :: xT :: yT :: Nil =>
            tryMove(xF, yF, action, xT, yT)
          case _ =>
            printString("unkown command")
            ReturnValues.INVALID
        }
    }
  }

  def tryMove(xF: String, yF: String, action: String, xT: String, yT: String) = {
    Try(xF.toInt, yF.toInt, xT.toInt, yT.toInt) match {
      case Success(v) =>
        action match {
          case "m" =>
            if (controller.move((xF.toInt, yF.toInt), (xT.toInt, yT.toInt)) == ReturnValues.VALID) {
              ReturnValues.VALID
            } else {
              printString("invalid move")
              ReturnValues.INVALID
            }
          case "a" =>
            if (controller.attack((xF.toInt, yF.toInt), (xT.toInt, yT.toInt)) == ReturnValues.VALID) {
              ReturnValues.VALID
            } else {
              printString("invalid attack")
              ReturnValues.INVALID
            }
          case _ =>
            printString("invalid move/attack")
            ReturnValues.INVALID
        }
      case Failure(e) =>
        printString("index is not a number")
        ReturnValues.INVALID
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

    println(State.gameState.toString())

    if (observerCommand == READSTRING) {
      controller.readString = scala.io.StdIn.readLine()
    }
    else if (observerCommand == READCOMMAND) {
      val command = scala.io.StdIn.readLine()
      playerTurn(command)
    }
  }
}
