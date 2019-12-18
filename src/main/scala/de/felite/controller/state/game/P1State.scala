package de.felite.controller.state.game

import de.felite.controller.GameControllerInterface
import de.felite.controller.state.game.GameStateString.GameState
import de.felite.util.ObserverCommand

case class P1State(controller:GameControllerInterface) extends CurrentState {
  val state:GameState = GameStateString.P1

  override def handle(): GameState = {
    controller.notifyObservers(ObserverCommand.PRINTSTRING)
    state
  }

  override def toString: String = {
    controller.getPlayerName + GameStateString.message(state)
  }
}
