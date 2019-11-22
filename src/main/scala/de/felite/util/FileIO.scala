package de.felite.util

import de.felite.model.entity.Entity
import de.felite.model.entity.obstacle.{Grass, Obstacle, Rock, Tree}
import scala.util.control.Breaks._

import scala.io.Source

object FileIO {
  private var scal: Int = _
  private val fileName: String = "src\\fieldbase.txt"

  def readFromFile(): Array[Array[Entity]] = {
    var c: Int = 0
    val arr: Array[Array[Entity]] = Array.ofDim(scal, scal)
    val src = Source.fromFile(fileName)
    breakable {
      for (v <- src.getLines()) {
        if (c >= scal) {
          break
        }
        var i: Int = 0
        for (x <- v.toArray.slice(0, scal)) { //{ .slice(1,2))
          arr(c)(i) = if (x.equals('r')) Rock else if (x.equals('t')) Tree else Grass
          i += 1
        }
        c += 1
      }
    }
    src.close()
    arr
    //    Array(Array(Grass,Tree,Tree),Array(Rock,Grass,Grass),Array(Grass,Grass,Grass))
  }

  def setScal(s: Int): Unit = {
    scal = s
  }

  def getScal: Int = {
    scal
  }

}
