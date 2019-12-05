package de.felite.controller.status

import de.felite.controller.status.GameStateString.GameState

class P2InitState extends CurrentState {
  val state:GameState = GameStateString.P2_INI

  override def handle(): Unit = ???

  override def toString(): String = {
    GameStateString.message(state)
  }
}
