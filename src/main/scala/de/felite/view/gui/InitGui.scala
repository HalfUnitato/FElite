package de.felite.view.gui

import de.felite.controller.GameController
import de.felite.controller.status.State
import de.felite.util.{Observer, ObserverCommand}

import scala.swing.{BorderPanel, Dimension, Frame, Label}

class InitGui(controller:GameController) extends Frame with Observer {

  controller.add(this)
  
  title = "Fire Emblem lite"
  preferredSize = new Dimension(640, 480)

  def borderPanel: BorderPanel = new BorderPanel {
//    add(new Label("Name of player number one:"), BorderPanel.Position.North)

  }

  visible = true


  
  controller.remove(this)
  override def update(observerCommand: ObserverCommand.Value): Unit = ???
}
