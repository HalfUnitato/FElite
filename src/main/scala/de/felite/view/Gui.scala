package de.felite.view

import de.felite.controller.GameController
import de.felite.controller.status.State
import de.felite.model.Field
import de.felite.util.{Observable, Observer, ObserverCommand}

import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._
import scala.io.Source._
import de.felite.util.ObserverCommand._
import javax.swing.JOptionPane

class Gui(controller: GameController) extends Frame with Observer{

  controller.add(this)


  val scale: Int = Field.getScale
  val statusline = new TextField(State.gameState.toString, 20)
  val statusTop = new Label(State.gameState.toString)

  var cells: Array[Array[CellButton]] = Array.ofDim[CellButton](scale, scale)

  title = "Fire Emblem lite"
  preferredSize = new Dimension(640,480)




  def gridPanel: GridPanel = new GridPanel(scale, scale) {
    border = LineBorder(java.awt.Color.BLACK, 2)
    background = java.awt.Color.BLACK
    for {
      column <- 0 until scale
      row <- 0 until scale
    } {
      val cellButton = new CellButton(row, column, controller)
      cells(row)(column) = cellButton
      cellButton.setView()
      contents += cellButton
      listenTo(cellButton)
    }
    reactions += {
      case ButtonClicked(cBtn: CellButton) =>
        println("Clicked the Button")
        buttonClick(cBtn)
        redraw()
      case _ =>
    }
  }

  private def buttonClick(btn:CellButton): Unit = {
    println("I am: " + btn.text)
    val x = btn.getX
    val y = btn.getY

    if (controller.btnStartCoord._1 == -1 && controller.btnStartCoord._2 == -1) {
      controller.btnStartCoord = (x,y)
    } else {
      controller.btnEndCoord = (x,y)
      if (!controller.tryMove(controller.btnStartCoord,(x,y))) {
        JOptionPane.showMessageDialog(null,"My Goodness this is so concise")
      }
      println("btnStartCoord:" + controller.btnStartCoord)
      println("btnEndCoord:" + controller.btnEndCoord)
      if (controller.btnStartCoord._1 != -1 && controller.btnEndCoord._1 != -1) {
        val tmp = cells(controller.btnStartCoord._1)(controller.btnStartCoord._2)
        cells(controller.btnStartCoord._1)(controller.btnStartCoord._2) = cells(controller.btnEndCoord._1)(controller.btnEndCoord._2)
        cells(controller.btnEndCoord._1)(controller.btnEndCoord._2) = tmp
      }
      cells(controller.btnStartCoord._1)(controller.btnStartCoord._2).remake()
      cells(controller.btnEndCoord._1)(controller.btnEndCoord._2).remake()
      repaint()
      controller.btnStartCoord = (-1,-1)
      controller.btnEndCoord = (-1,-1)
    }
  }


  contents = new BorderPanel {
    add(statusTop, BorderPanel.Position.North)
    add(gridPanel, BorderPanel.Position.Center)
    add(statusline, BorderPanel.Position.South)
  }

  visible = true
  redraw()


  def redraw(): Unit = {
    for {
      row <- 0 until Field.getScale
      column <- 0 until Field.getScale
    } cells(row)(column).remake()
//    statusline.text = controller.printString
    repaint
  }

  override def update(observerCommand: ObserverCommand.Value): Unit = {
    println(State.gameState.toString)
    redraw()
    if (observerCommand == READSTRING) {
      //popup
      controller.readString = "Klaus"
    }
  }
}