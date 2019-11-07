package de.felite.io

import de.felite.Field

object Tui {
  // return (-1,-1) at exit
  def readLine(what: String): String = {
    println(what)
    scala.io.StdIn.readLine()
  }

  def printField(field: Field): Unit = {
    var base = ""
    base += "\t0\t1\t2\t3\t4\t5\n"
    var i = 0
    for (y <- field.getField) {
      base += i + "\t"
      for (x <- y) {
        //        print(x.sign() + "\t")
        base += x.sign() + "\t"
      }
      i += 1
//      println()
      base += "\n"
    }

    print(base)
  }

  def printString(txt: String): Unit = {
    println(txt)
  }
}
