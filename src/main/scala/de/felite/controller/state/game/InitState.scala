package de.felite.controller.state.game

import de.felite.controller.GameControllerInterface
import de.felite.controller.component.controllerBaseImpl.GameController
import de.felite.controller.state.game.GameStateString.GameState
import de.felite.util.ObserverCommand

case class InitState(controller: GameControllerInterface) extends CurrentState {
  val state: GameState = GameStateString.INIT

  override def handle(): GameState = {
    controller.notifyObservers(ObserverCommand.PRINTSTRING)
    state
  }

  override def toString: String = {
    GameStateString.message(state)
  }
}
