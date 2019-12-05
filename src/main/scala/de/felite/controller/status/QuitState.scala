package de.felite.controller.status

import de.felite.controller.status.GameStateString.GameState

class QuitState() extends CurrentState {
  val state:GameState = GameStateString.QUIT

  override def handle(): Unit = ???

  override def toString(): String = {
    GameStateString.message(state)
  }
}
