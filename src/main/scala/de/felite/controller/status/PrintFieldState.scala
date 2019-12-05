package de.felite.controller.status

import de.felite.controller.GameController
import de.felite.controller.status.GameStateString.GameState

class PrintFieldState(contoller:GameController) extends CurrentState {
  val state:GameState = GameStateString.PRINT_FIELD

  override def handle(): Unit = ???

  override def toString(): String = {
    GameStateString.message(state) + "\n" + contoller.FieldToString
  }
}
