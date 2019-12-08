package de.felite.view

import de.felite.controller.GameController
import de.felite.controller.status.State
import de.felite.model.Field
import de.felite.util.{Observer, ObserverCommand}

import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._
import scala.io.Source._
import de.felite.util.ObserverCommand._

class Gui(controller: GameController) extends Frame with Observer {

  controller.add(this)

  title = "HTWG Sudoku"
  var cells = Array.ofDim[CellPanel](Field.getScal, Field.getScal)


  def gridPanel = new GridPanel(controller.blockSize, controller.blockSize) {
    border = LineBorder(java.awt.Color.BLACK, 2)
    background = java.awt.Color.BLACK
    for {
      outerRow <- 0 until controller.blockSize
      outerColumn <- 0 until controller.blockSize
    } {
      contents += new GridPanel(controller.blockSize, controller.blockSize) {
        border = LineBorder(java.awt.Color.BLACK, 2)
        for {
          innerRow <- 0 until controller.blockSize
          innerColumn <- 0 until controller.blockSize
        } {
          val x = outerRow * controller.blockSize + innerRow
          val y = outerColumn * controller.blockSize + innerColumn
          val cellPanel = new CellPanel(x, y, controller)
          cells(x)(y) = cellPanel
          contents += cellPanel
          listenTo(cellPanel)
        }
      }
    }
  }

  val statusline = new TextField(State.gameState.toString(), 20)

  contents = new BorderPanel {
    //add(highlightpanel, BorderPanel.Position.North)
    add(gridPanel, BorderPanel.Position.Center)
    add(statusline, BorderPanel.Position.South)
  }

  visible = true
  redraw

//  reactions += {
//    case event: GridSizeChanged => resize(event.newSize)
//    case event: CellChanged => redraw
//    case event: CandidatesChanged => redraw
//  }


  def redraw = {
    for {
      row <- 0 until Field.getScal
      column <- 0 until Field.getScal
    } cells(row)(column).redraw
    statusline.text = controller.printString
    repaint
  }

  override def update(observerCommand: ObserverCommand.Value): Unit = {

    println(State.gameState.toString())
    redraw
    if (observerCommand == READSTRING) {
      //popup
      controller.readString = "Klaus"
    }
  }
}