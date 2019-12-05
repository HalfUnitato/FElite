package de.felite.controller.status

import de.felite.controller.status.GameStateString.GameState

class InitState extends CurrentState {
  val state:GameState = GameStateString.INIT

  override def handle(): Unit = ???

  override def toString(): String = {
    GameStateString.message(state)
  }
}
