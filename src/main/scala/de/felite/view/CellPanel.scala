package de.felite.view

import de.felite.controller.GameController
import de.felite.model.Field

import scala.swing.{BoxPanel, Color, Dimension, FlowPanel, Font, Label, Orientation, Swing}

//import scala.swing._
import scala.swing.event.MouseClicked
//import scala.swing.event._

class CellPanel(x: Int, y: Int, controller: GameController) extends FlowPanel {

  //val givenCellColor = new Color(200, 200, 255)
  val cellColor = new Color(224, 224, 255)
  //val highlightedCellColor = new Color(192, 255, 192)

  def myCell = Field.getCell(x, y)

  def cellText(x: Int, y: Int):String  = Field.getCell(x, y).sign().toString

  val label =
    new Label {
      text = cellText(x, y)
      font = new Font("Verdana", 1, 36)
    }

  val cell = new BoxPanel(Orientation.Vertical) {
    contents += label
//    preferredSize = new Dimension(150, 50)
    //background = if (controller.isGiven(row, column)) givenCellColor else cellColor
    border = Swing.BeveledBorder(Swing.Raised)
    listenTo(mouse.clicks)

    reactions += {
      case MouseClicked(src,pt,mod,clicks,pops) => {
        // speicher kordinaten fuer den move-befehl!!
      }
    }
  }

  def redraw = {
    contents.clear()
    label.text = cellText(x, y)
    contents += cell
    repaint
  }
}