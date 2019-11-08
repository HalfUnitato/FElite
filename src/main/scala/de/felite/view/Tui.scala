package de.felite.view

import de.felite.controller._
import de.felite.model.{Field, Player}
import de.felite.util.{Observer, ReturnValues}

class Tui(controller:GameController) extends Observer{

  controller.add(this)

  // return (-1,-1) at exit
  /*def readLine(what: String): String = {
    println(what)
    scala.io.StdIn.readLine()
  }*/


  def init(input: String): Unit = {
    val names = input.split(" ")
    controller.init(names)
    controller.infoToString()
    //print basic game?
  }

 /* def playerTurn(input: String): ReturnValues.Value = {
    controller.getPlayers()

    val fieldString = Tui.createFieldString(GameControl.fieldTxt)

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
  }*/

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

  override def update(): Unit = {
    /*if (option == 0) {
      printString(controller.infoToString())
    } else if (option ==1) {

    }*/

  }
}
