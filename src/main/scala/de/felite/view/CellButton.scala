package de.felite.view

import java.awt.Color

import de.felite.model.Field
import de.felite.model.entity.Entity

import scala.swing.{Button, Font, Label}

class CellButton(x: Int, y: Int) extends Button {

  def myCell: Entity = Field.getCell(x, y)

  def getCellSign(x: Int, y: Int): String = myCell.sign().toString

  val cellText: String = getCellSign(x,y)

  text = cellText
  font = new Font("Verdana", 1, 36)

  val brown = new Color(51,25,0)

  def setView(): Unit = {
    val str = myCell.getColor
    str match {
      case Console.BLACK =>
        background = Color.BLACK
        foreground = Color.WHITE
      case Console.RED =>
        background = Color.GREEN
        foreground = Color.RED
      case Console.BLUE =>
        background = Color.GREEN
        foreground = Color.BLUE
      case Console.GREEN =>
        //Grass
        background = Color.GREEN
        text = ""
      case Console.CYAN =>
        //Rock
        background = Color.GRAY
        text = ""
      case Console.MAGENTA =>
        //Tree
        background = brown
        text = ""
      case _ =>
        Color.BLACK

    }
  }
}
