package de.felite.controller.status

import de.felite.controller.GameController
import de.felite.controller.status.GameStateString.GameState

class P2State(controller:GameController) extends CurrentState {
  val state:GameState = GameStateString.P2
  override def handle(): Unit = ???

  override def toString(): String = {
    controller.getPlayerName + GameStateString.message(state)
  }
}