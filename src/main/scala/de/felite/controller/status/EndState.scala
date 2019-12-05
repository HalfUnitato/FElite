package de.felite.controller.status

import de.felite.controller.GameController
import de.felite.controller.status.GameStateString.GameState

class EndState(controller:GameController) extends CurrentState {
  val state:GameState = GameStateString.END

  override def handle(): Unit = ???

  override def toString(): String = {
    GameStateString.message(state)
  }
}
