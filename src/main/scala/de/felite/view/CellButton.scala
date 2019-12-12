package de.felite.view

import java.awt.Color

import de.felite.controller.GameController
import de.felite.model.Field
import de.felite.model.entity.Entity

import scala.swing.event.MouseClicked
import scala.swing.{Button, Component, Font, Label, Point}

class CellButton(x: Int, y: Int, controller: GameController) extends Button {

  def myCell: Entity = Field.getCell(x, y)

  def getCellSign(x: Int, y: Int): String = myCell.sign().toString

  val brown = new Color(51, 25, 0)

  val cellText: String = getCellSign(x, y)


  text = cellText
  font = new Font("Verdana", 1, 36)

  listenTo(mouse.clicks)
  reactions += {
    case MouseClicked(src, _, _, _, _) => buttonClick(src)
  }

  private def buttonClick(srcCmp: Component): Unit = {
    println("I am a " + text)
    println("I am Button at" + x + y)
    controller.btnCoord = (x.toString,y.toString)
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
      //        text = ""
      case Console.CYAN =>
        //Rock
        background = Color.GRAY
      //        text = ""
      case Console.MAGENTA =>
        //Tree
        background = brown
      //        text = ""
      case _ =>
        background = Color.BLACK

    }
  }

}
