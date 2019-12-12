package de.felite.view

import java.awt.Color

import de.felite.controller.GameController
import de.felite.model.Field
import de.felite.model.entity.Entity
import de.felite.util.{Observer, ObserverCommand}
import javax.swing.JOptionPane

import scala.swing.event.MouseClicked
import scala.swing.{Button, Component, Font, Label, Point, Swing}

class CellButton(x: Int, y: Int, controller: GameController) extends Button with Observer {
  controller.add(this)

  def myCell: Entity = Field.getCell(x, y)

  def getCellSign(x: Int, y: Int): String = myCell.sign().toString

  val brown = new Color(51, 25, 0)

  val cellText: String = getCellSign(x, y)
  border = Swing.BeveledBorder(Swing.Lowered)


  text = cellText
  font = new Font("Verdana", 1, 36)

  listenTo(mouse.clicks)
  reactions += {
    case MouseClicked(src,pt,mod,clicks,pops) => buttonClick(src)
  }

  private def buttonClick(srcCmp: Component): Unit = {
    /*println("I am a " + text)
    println("I am Button at" + x + y)*/
    if (controller.btnCoord._1 == -1 && controller.btnCoord._2 == -1) {
      controller.btnCoord = (x,y)
    } else {
      if (!controller.tryMove(controller.btnCoord,(x,y))) {
        JOptionPane.showMessageDialog(null,"My Goodness this is so concise")
      }
//      controller.sta
      controller.btnCoord = (-1,-1)
    }
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

  override def update(observerCommand: ObserverCommand.Value): Unit = setView()
}
