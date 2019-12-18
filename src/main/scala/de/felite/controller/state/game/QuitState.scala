package de.felite.controller.state.game

import de.felite.controller.GameControllerInterface
import de.felite.controller.state.game.GameStateString.GameState
import de.felite.util.ObserverCommand

case class QuitState(controller:GameControllerInterface) extends CurrentState {
  val state:GameState = GameStateString.QUIT

  override def handle(): GameState = {
    controller.notifyObservers(ObserverCommand.PRINTSTRING)
    System.exit(0)
    state
  }

  override def toString: String = {
    GameStateString.message(state)
  }
}
