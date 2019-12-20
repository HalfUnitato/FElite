package de.felite

//import com.google.inject.Guice
import de.felite.controller.GameControllerInterface
import de.felite.controller.component.controllerImpl._
import de.felite.model.{Field, Player}
import de.felite.util.ObserverCommand
import de.felite.view.Tui
import de.felite.controller.state.game.GameStateString._
import de.felite.controller.state.game.{GameStateString, State}
import de.felite.view.gui.GameGui

object FElite {

  private val fieldSrc = "src\\fieldTest.txt"

  def main(args: Array[String]): Unit = {

    println("Welcome to Fire Emblem lite")
//    val injector = Guice.createInjector(new FEliteModule)
    val controller: GameControllerInterface = new controllerBaseImpl.GameController//injector.getInstance(classOf[GameControllerInterface])

    controller.init()

    val tui = new Tui(controller)
    new GameGui(controller)



    while (controller.state.gameState.state != END && controller.state.gameState.state != QUIT) {
      tui.playerTurn(scala.io.StdIn.readLine())
    }
  }
}
