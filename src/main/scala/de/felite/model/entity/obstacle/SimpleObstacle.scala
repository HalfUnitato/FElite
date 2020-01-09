package de.felite.model.entity.obstacle

import de.felite.model.Obstacle

import scala.xml.Elem

case class SimpleObstacle(sign: Char, x: Int, y: Int, _walkthrough: Boolean) extends Obstacle {
  override val walkThrough: Boolean = _walkthrough
  override val color: String = if (sign == 't') Console.MAGENTA else if (sign == 'r') Console.CYAN else Console.GREEN

  override def toXML: Elem = {
    <obstacle row={y.toString} col={x.toString}>
      {sign}
    </obstacle>
  }
}