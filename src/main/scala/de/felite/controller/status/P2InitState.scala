package de.felite.controller.status

import de.felite.controller.GameController
import de.felite.controller.status.GameStateString.GameState
import de.felite.util.ObserverCommand

case class P2InitState(controller:GameController) extends CurrentState {
  val state:GameState = GameStateString.P2_INI

  override def handle() = {
    controller.notifyObservers(ObserverCommand.READSTRING)
    state
  }

  override def toString(): String = {
    GameStateString.message(state)
  }
}
