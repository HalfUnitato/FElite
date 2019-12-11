package de.felite.view


import de.felite.controller.GameController
import de.felite.controller.status._
import de.felite.util.{Observer, ObserverCommand}
import de.felite.util.ObserverCommand._

import scala.util.{Failure, Success, Try}

class Tui(controller: GameController) extends Observer {
  controller.add(this)

  def playerTurn(input: String): Boolean = {
    //input is useless?
    //where to put input reading
    input match {
      case "undo" =>
        controller.undo
        true
      case "redo" =>
        controller.redo
        true
      case "p" =>
        printString(controller.FieldToString)
        true
      case "quit" =>
        State.gameState = new QuitState(controller)
        State.gameState.handle
        true
      /*case "cancel" =>
        controller.gameState = ReturnValues.CANCEL
        true*/
      case "end" =>
        State.gameState = new EndState(controller)
        State.gameState.handle
        true
      case "help" =>
        printHelp()
        true
      case _ =>
        input.split(" ").toList match {
          case xF :: yF :: xT :: yT :: Nil =>
            tryMove(xF, yF, xT, yT)
          case _ =>
            printString("unkown command")
            false
        }
    }
  }

  def tryMove(xF: String, yF: String, xT: String, yT: String):Boolean = {
    Try(xF.toInt, yF.toInt, xT.toInt, yT.toInt) match {
      case Success(v) =>
            if (controller.movement((xF.toInt, yF.toInt), (xT.toInt, yT.toInt))) {
              true
            } else {
              printString("invalid move")
              false
            }
      case Failure(e) =>
        printString("index is not a number")
        false
    }
  }

  def printString(txt: String): Boolean = {
    println(txt)
    true
  }

  def printHelp(): Boolean = {
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
    true
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
