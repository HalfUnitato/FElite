package de.felite.io

import de.felite.Field

object Tui {
  // return (-1,-1) at exit
  def readLine(what: String) = {
    println(what)
    scala.io.StdIn.readLine()
  }

  def printField(field: Field): Unit = {
    for (y <- field.getField) {
      for (x <- y) {
        print(x.sign() + "\t")
      }
      println()
    }
  }

  def printString(txt: String): Unit = {
    println(txt)
  }
}
