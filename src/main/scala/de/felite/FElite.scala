package de.felite

import de.felite.controller.GameController
import de.felite.model.{Field, Player}
import de.felite.util.ObserverCommand
import de.felite.view.Tui
import de.felite.controller.status.GameStateString._
import de.felite.controller.status.{GameStateString, InitState, NextCmdState, State}
import de.felite.view.gui.GameGui

object FElite {

  private val fieldSrc = "src\\fieldTest.txt"
  //val controller = new GameController // second param is scal value

  def main(args: Array[String]): Unit = {

    println("Welcome to Fire Emblem lite")
    val controller: GameController = new GameController()
    val tui = new Tui(controller)

    controller.init()

    //    val gui = new GameGui(controller)


    while (!controller.isEnd && (State.gameState.state != END && State.gameState.state != QUIT)) {
      tui.playerTurn(scala.io.StdIn.readLine())
    }
  }
}
