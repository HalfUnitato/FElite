package de.felite.view

import de.felite.model.Field
import de.felite.util.ReturnValues

object Tui {
  // return (-1,-1) at exit
  def readLine(what: String): String = {
    println(what)
    scala.io.StdIn.readLine()
  }

  def createFieldString(field: Field): String = {
    var base = ""
    base += "\t0\t1\t2\t3\t4\t5\n"
    var i = 0
    for (y <- field.getField) {
      base += i + "\t"
      for (x <- y) {
        base += x.sign() + "\t"
      }
      i += 1
      base += "\n"
    }
    base
  }


  def printString(txt: String): Unit = {
    println(txt)
  }

  def printHelp(): ReturnValues.Value = {
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
    ReturnValues.VALID
  }
}
