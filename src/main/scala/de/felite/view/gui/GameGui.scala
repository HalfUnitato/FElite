package de.felite.view.gui

import de.felite.controller.GameControllerInterface
import de.felite.controller.state.game.{EndState, QuitState, State}
import de.felite.controller.GameController
import de.felite.controller.status.{EndState, QuitState, State}
import de.felite.controller.status.GameStateString._
import de.felite.model.Field
import de.felite.util.ObserverCommand._
import de.felite.util.{Observer, ObserverCommand}
import javax.swing.JOptionPane

import scala.swing.Swing.LineBorder
import scala.swing._
import scala.swing.event._

class GameGui(controller: GameControllerInterface) extends Frame with Observer {

  controller.add(this)

  val scale: Int = Field.getScale
  def statusLine: FlowPanel = new FlowPanel() { //this should contain all static Information per Player
    def getTurnName:String = State.gameState.toString
    var turnName: Label = new Label(getTurnName)
    contents += turnName
  }


  def commandLine: FlowPanel = new FlowPanel() {
    contents += new Button(Action("End Turn") {
      State.gameState = EndState(controller)
      State.gameState.handle()
    })
    contents += new Button(Action("Quit Game") {
      State.gameState = QuitState(controller)
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
//        println("Clicked the Button")
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
      if (!controller.tryMove(controller.btnStartCoord, (x, y))) {
        JOptionPane.showMessageDialog(null, "Move not valid")
        controller.btnStartCoord = (-1, -1)
        controller.btnEndCoord = (-1, -1)
      } else {
//        println("btnStartCoord:" + controller.btnStartCoord)
//        println("btnEndCoord:" + controller.btnEndCoord)
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


  def mainPanel:BorderPanel = { new BorderPanel {
      add(statusLine, BorderPanel.Position.North)
      add(gridPanel, BorderPanel.Position.Center)
      add(commandLine, BorderPanel.Position.South)
    }
  }

  contents = mainPanel

  menuBar = new MenuBar {
    contents += new Menu("File") {
      contents += new MenuItem(Action("Quit") {
        State.gameState = QuitState(controller)
        State.gameState.handle()
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
    repaint
    centerOnScreen()
  }


  override def update(observerCommand: ObserverCommand.Value): Unit = {
    //println(State.gameState.toString)
    val state = State.gameState.state
    if (state == P1 || state == P2) {
      contents = mainPanel
    }
    if (state == QUIT) {
      JOptionPane.showMessageDialog(null, "Game Over \nThe Winner is ???")
      closeOperation()
    }
    redraw()
  }
}