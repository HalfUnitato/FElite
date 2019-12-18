package de.felite.controller.state.game

import de.felite.controller.GameControllerInterface
import de.felite.controller.state.game.GameStateString.GameState
import de.felite.util.ObserverCommand

case class P1InitState(controller:GameControllerInterface) extends CurrentState {
  val state:GameState = GameStateString.P1_INI

  override def handle(): GameState = {
    controller.notifyObservers(ObserverCommand.READSTRING)
    state
  }

  override def toString: String = {
    GameStateString.message(state)
  }
}
