package de.felite.view

import java.lang.ModuleLayer.Controller

import de.felite.controller._
import de.felite.model.{Field, Player}
import de.felite.util.{Observer, ObserverCommand, ReturnValues}

class Tui(controller:GameController) extends Observer{

  controller.add(this)

  // return (-1,-1) at exit
  /*def readLine(what: String): String = {
    println(what)
    scala.io.StdIn.readLine()
  }*/


  /*def init(input: String): Unit = {
    val names = input.split(" ")
    controller.init()
    controller.infoToString()
    //print basic game?
  }*/

  def playerTurn(input: String) = {
    //controller.getPlayers()

    //val fieldString = Tui.createFieldString(GameControl.fieldTxt)
    val command = scala.io.StdIn.readLine()
    command match {
      case "p" => printString(controller.field.toString)
      case "quit" =>  controller.gameState = ReturnValues.QUIT
      case "cancel" => controller.gameState = ReturnValues.CANCEL
      case "end" => controller.gameState = ReturnValues.END
      case "help" => printHelp()
      case _ =>
        command.toList.filter(c => c != ' ').map(c => c.toString) match {
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
              case _ => printString("invalid command/action") // -1 5 m 2 2 returns invalid command but should return invalid move
            }
          case _ => printString("invalid command")
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
    ReturnValues.VALID
  }

  override def update(observerCommand: ObserverCommand.Value): Unit = {
    if (observerCommand == ObserverCommand.PRINTSTRING)
    {
      printString(controller.printString)
    } else if (observerCommand == ObserverCommand.READSTRING)
    {
      controller.readString= scala.io.StdIn.readLine()
    } else if (observerCommand == ObserverCommand.READCOMMAND)
    {
      playerTurn(controller.printString)
    }
  }
}
