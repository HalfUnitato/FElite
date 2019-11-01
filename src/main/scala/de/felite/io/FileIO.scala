package de.felite.io

import scala.io.Source

object FileIO {
  def readFromFile(fileName: String): Array[Array[Char]] = {
    var c = 0
    var arr: Array[Array[Char]] = Array.ofDim(0, 0)
    val source = Source.fromFile(fileName)
    for (v <- source.getLines()) {
      if (c == 0) {
        val arrSize = v.split(",")
        arr = Array.ofDim(arrSize(1).toString.toInt, arrSize(0).toString.toInt)
      } else {
        arr(c - 1) = v.toArray //(v.split(",")).toArray[Char]
      }
      c += 1
    }

    source.close()
    arr
  }
}
