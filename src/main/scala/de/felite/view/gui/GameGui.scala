package de.felite.view.gui

import de.felite.controller.GameController
import de.felite.controller.status.{EndState, QuitState, State}
import de.felite.model.Field
import de.felite.util.ObserverCommand._
import de.felite.util.{Observer, ObserverCommand}
import javax.swing.JOptionPane

import scala.swing.Swing.LineBorder
import scala.swing._
import scala.swing.event._

class GameGui(controller: GameController) extends Frame with Observer {

  controller.add(this)

  override def closeOperation(): Unit = {
    //gameState set to quit
    System.exit(0)
  }

  val scale: Int = Field.getScale
  def statusTop(): FlowPanel = new FlowPanel() { //new Label(State.gameState.toString) //this should contain all static Informations per Player
    contents += new Label(State.gameState.toString) //not updated on end turn
  }


  def statusLine(): FlowPanel = new FlowPanel() { //new TextField(State.gameState.toString, 20)
    contents += new Button(Action("End Turn") {
      State.gameState = EndState(controller)
      State.gameState.handle()
    })
  }


  var cells: Array[Array[CellButton]] = Array.ofDim[CellButton](scale, scale)

  title = "Fire Emblem lite"
  preferredSize = new Dimension(640, 480)


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
      case _ =>
    }
  }

  private def buttonClick(btn: CellButton): Unit = {
    //    println("I am: " + btn.text)
    val x = btn.getX
    val y = btn.getY

    if (controller.btnStartCoord._1 == -1 && controller.btnStartCoord._2 == -1) {
      controller.btnStartCoord = (x, y)
    } else {
      controller.btnEndCoord = (x, y)
      val bool = controller.tryMove(controller.btnStartCoord, (x, y))
      println(bool)
      if (!bool) {
        println("invalid move")
        JOptionPane.showMessageDialog(null, "My Goodness this is so concise")
        controller.btnStartCoord = (-1, -1)
        controller.btnEndCoord = (-1, -1)
      } else {
        println("btnStartCoord:" + controller.btnStartCoord)
        println("btnEndCoord:" + controller.btnEndCoord)
        if (controller.btnStartCoord._1 != -1 && controller.btnEndCoord._1 != -1) {
          val tmp = cells(controller.btnStartCoord._1)(controller.btnStartCoord._2)
          cells(controller.btnStartCoord._1)(controller.btnStartCoord._2) = cells(controller.btnEndCoord._1)(controller.btnEndCoord._2)
          cells(controller.btnEndCoord._1)(controller.btnEndCoord._2) = tmp
        }
        cells(controller.btnStartCoord._1)(controller.btnStartCoord._2).remake()
        cells(controller.btnEndCoord._1)(controller.btnEndCoord._2).remake()
        controller.btnStartCoord = (-1, -1)
        controller.btnEndCoord = (-1, -1)
      }
    }
  }


  contents = new BorderPanel {
    add(statusTop(), BorderPanel.Position.North)
    add(gridPanel, BorderPanel.Position.Center)
    add(statusLine(), BorderPanel.Position.South)
  }

  menuBar = new MenuBar {
    contents += new Menu("File") {
      contents += new MenuItem(Action("Quit") {
        State.gameState = QuitState(controller)
        State.gameState.handle()
//        System.exit(0)
      })
    }
    contents += new Menu("Edit") {
      contents += new MenuItem(Action("Undo") {
        controller.undo()
      })
      contents += new MenuItem(Action("Redo") {
        controller.redo()
      })

    }
    contents += new Menu("Options") {

    }
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