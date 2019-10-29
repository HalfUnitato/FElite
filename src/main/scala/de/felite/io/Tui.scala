package de.felite.io

import de.felite.Field

object Tui {
  // return (-1,-1) at exit
  def readCoordiantes(what: String): (Int, Int) = {
    println(what)
    println("format like: x,y\tc for cancel")
    var loop = true
    while (loop) {
      try {
        val arrCoord: Array[String] = scala.io.StdIn.readLine().split(",")
        if (arrCoord(0).equals("c"))
          loop = false
        else
          return (arrCoord(0).toInt, arrCoord(1).toInt)
      }
      catch {
        case e: Exception => println("wrong format")
      }
    }
    (-1, -1) // userAbbruch
  }

  def inputDouble(what: String): Double = {
    println(what)
    println("format like: x\tc for cancel")
    var loop = true
    var ret = ""
    while (loop) {
      try {
        ret = scala.io.StdIn.readLine()
        return ret.toDouble
      }
      catch {
        case e: Exception =>
          if (ret.equals("c"))
            loop = false
          else
            println("wrong format")
      }
    }
    -1 // userAbbruch
  }

  def inputInt(what: String): Int = {
    println("format like: x\tc for cancel")
    var loop = true
    var ret = ""
    while (loop) {
      try {
        ret = scala.io.StdIn.readLine()
        return ret.toInt
      }
      catch {
        case e: Exception =>
          if (ret.equals("c"))
            loop = false
          else
            println("wrong format")
      }
    }
    -1 // userAbbruch
  }

  def inputChar(what: String): Char = {
    println("format like: x\tc for cancel")
    var loop = true
    while (loop) {
      try {
        val ret = scala.io.StdIn.readChar()
        return ret
      }
      catch {
        case e: Exception =>
          println("wrong format")
      }
    }
    'c' // userAbbruch
  }

  def printField(field: Field): Unit = {
    for (y <- field.getField) {
      for (x <- y) {
        print(x)
      }
      println()
    }
  }
}
