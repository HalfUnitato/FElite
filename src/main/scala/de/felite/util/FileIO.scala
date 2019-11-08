package de.felite.util

import de.felite.model.obstacle.{Branded, Grass, Rock, Tree}

import scala.io.Source

object FileIO {
  def readFromFile(fileName: String): Array[Array[Branded]] = {
    var c = 0
    var arr: Array[Array[Branded]] = Array.ofDim(0, 0)
    val source = Source.fromFile(fileName)
    for (v <- source.getLines()) {
      if (c == 0) {
        val arrSize = v.split(",")
        arr = Array.ofDim(arrSize(1).toString.toInt, arrSize(0).toString.toInt)
      } else {
        var i: Int = 0
        for (x <- v.toArray) {
          arr(c - 1)(i) = if (x.equals('r')) Rock else if (x.equals('t')) Tree else Grass
          i += 1
        }
      }
      c += 1
    }
    source.close()
    arr
  }
}
