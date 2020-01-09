package de.felite.util

import de.felite.model.{Entity, ObstacleFactory}

import scala.io.Source
import scala.util.control.Breaks._

object FileIO {
  private var scal: Int = _
  private var fileName: String = _

  def readFromFile(scal: Int, fileName: String): Array[Array[Option[Entity]]] = {
    this.scal = scal
    this.fileName = fileName
    var c: Int = 0
    val arr: Array[Array[Option[Entity]]] = Array.ofDim(scal, scal)
    val src = Source.fromFile(fileName)
    breakable {
      for (v <- src.getLines()) {
        if (c >= scal) {
          break
        }
        var i: Int = 0
        for (x <- v.toArray.slice(0, scal)) { //{ .slice(1,2))
          arr(c)(i) = Some(ObstacleFactory.create(x,c,i))
          i += 1
        }
        c += 1
      }
    }
    src.close()
    arr
    //    Array(Array(Grass,Tree,Tree),Array(Rock,Grass,Grass),Array(Grass,Grass,Grass))
  }

  def getScal: Int = {
    scal
  }

}
