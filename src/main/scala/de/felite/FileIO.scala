package de.felite

import io.Source._
import scala.io.Source

object FileIO {
  def readFromFile(fileName: String): Array[Array[String]] = {
    var c = 0
    var arr: Array[Array[String]] = Array.ofDim(0, 0)
    val source = Source.fromFile(fileName)
    for (v <- source.getLines()) {
      if (c == 0) {
        arr = Array.ofDim(v(1), v(0))
      } else {
        arr(c - 1) = v.split(",")
      }
      c += 1
    }

    source.close()
    arr
  }
}