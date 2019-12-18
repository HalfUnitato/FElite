package de.felite.view


import de.felite.controller.GameControllerInterface
import de.felite.controller.state._
import de.felite.controller.state.game.{EndState, PrintFieldState, QuitState, State}
import de.felite.util.{Observer, ObserverCommand}
import de.felite.util.ObserverCommand._

import scala.util.{Failure, Success, Try}

class Tui(controller: GameControllerInterface) extends Observer {
  controller.add(this)


  def playerTurn(input: String): Boolean = {
    //input is useless?
    //where to put input reading
    input match {
      case "undo" =>
        controller.undo()
        true
      case "redo" =>
        controller.redo()
        true
      case "p" =>
        controller.state.gameState = PrintFieldState(controller)
        controller.state.gameState.handle()
        //        printString(controller.FieldToString)
        true
      case "quit" =>
        controller.state.gameState = QuitState(controller)
        controller.state.gameState.handle()
        true
      /*case "cancel" =>
        controller.gameState = ReturnValues.CANCEL
        true*/
      case "end" =>
        controller.state.gameState = EndState(controller)
        controller.state.gameState.handle()
        true
      case "help" =>
        printHelp()
        true
      case _ =>
        input.split(" ").toList match {
          case xF :: yF :: xT :: yT :: Nil =>
            Try(xF.toInt, yF.toInt, xT.toInt, yT.toInt) match {
              case Success(v) =>
                controller.btnStartCoord = (xF.toInt, yF.toInt)
                controller.btnEndCoord = (xT.toInt, yT.toInt)
                if (!controller.doMove) {
                  println("move not valid")
                  false
                } else {
                  true
                }
              case Failure(v) =>
                println("invalid coordinates!")
                false
            }
          case _ =>
            printString("unknown command")
            false
        }
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

    println(controller.state.gameState.toString)

    if (observerCommand == READSTRING) {
      controller.readString = scala.io.StdIn.readLine()
    }
    else if (observerCommand == READCOMMAND) {
      val command = scala.io.StdIn.readLine()
      playerTurn(command)
    }
  }
}
