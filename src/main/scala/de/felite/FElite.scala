package de.felite

import com.google.inject.Guice
import de.felite.controller.GameControllerInterface
import de.felite.controller.state.game.GameStateString._
import de.felite.view.Tui
import de.felite.view.gui.GameGui

object FElite {

  def main(args: Array[String]): Unit = {

    println("Welcome to Fire Emblem lite")

    val injector = Guice.createInjector(new FEliteModule)
    val controller = injector.getInstance(classOf[GameControllerInterface])

    controller.init()

    val tui = new Tui(controller)
    new GameGui(controller)

    while (controller.state.gameState.state != END && controller.state.gameState.state != QUIT) {
      tui.playerTurn(scala.io.StdIn.readLine())
    }
  }
}
