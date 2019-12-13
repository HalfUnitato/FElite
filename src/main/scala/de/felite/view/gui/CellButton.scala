package de.felite.view.gui

import java.awt.Color

import de.felite.controller.GameController
import de.felite.model.Field
import de.felite.model.entity.Entity

import scala.swing.{Button, Font, Swing}

class CellButton(x: Int, y: Int, controller: GameController) extends Button{

  def getX:Int = x
  def getY:Int = y

  var myCell: Entity = Field.getCell(x, y)

  def getCellSign(x: Int, y: Int): String = myCell.sign().toString

  val brown = new Color(51, 25, 0)

  def cellText: String = getCellSign(x, y)
  border = Swing.BeveledBorder(Swing.Lowered)



  font = new Font("Verdana", 1, 36)

  def remake(): Unit = {
    myCell = Field.getCell(x,y)
    text = cellText
    setView()
  }

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
        foreground = Color.BLACK
      case Console.CYAN =>
        //Rock
        background = Color.GRAY
        foreground = Color.BLACK
      case Console.MAGENTA =>
        //Tree
        background = brown
        foreground = Color.BLACK
      case _ =>
        // should never happen
        background = Color.BLACK
        foreground = Color.WHITE

    }
  }

}
