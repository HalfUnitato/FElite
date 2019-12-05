package de.felite.controller.status

import de.felite.controller.status.GameStateString.GameState

class P1InitState extends CurrentState {
  val state:GameState = GameStateString.P1_INI

  override def handle(): Unit = ???

  override def toString(): String = {
    GameStateString.message(state)
  }
}
