package de.felite.controller.status

import de.felite.controller.status.GameStateString.GameState

class NextCmdState extends CurrentState {
  val state:GameState = GameStateString.NEXT_CMD

  override def handle(): Unit = ???

  override def toString(): String = {
    GameStateString.message(state)
  }
}
