package de.felite.util

import de.felite.model.entity.Entity
import de.felite.model.entity.obstacle.{Grass, Obstacle, Rock, Tree}

import scala.io.Source

object FileIO {
  var scal: Int = _

  def readFromFile(fileName: String): Array[Array[Entity]] = {
    var c = 0
    val arr: Array[Array[Entity]] = Array.ofDim(scal, scal)
    val source = Source.fromFile(fileName)
    for (v <- source.getLines()) {
      if (c >= scal) {
        source.close()
        return arr
      }
      var i: Int = 0
      for (x <- v.toArray.slice(0,scal)) { //{ .slice(1,2))
        arr(c)(i) = if (x.equals('r')) Rock else if (x.equals('t')) Tree else Grass
        i += 1
      }
      c += 1
    }

    source.close()
    arr
  }

}
