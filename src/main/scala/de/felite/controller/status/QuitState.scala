package de.felite.controller.status

import de.felite.controller.{GameController, GameControllerInterface}
import de.felite.controller.status.GameStateString.GameState
import de.felite.util.ObserverCommand

case class QuitState(controller:GameControllerInterface) extends CurrentState {
  val state:GameState = GameStateString.QUIT

  override def handle(): GameState = {
    controller.notifyObservers(ObserverCommand.PRINTSTRING)
    state
  }

  override def toString(): String = {
    GameStateString.message(state)
  }
}
