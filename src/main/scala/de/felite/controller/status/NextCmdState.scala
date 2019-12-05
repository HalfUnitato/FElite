package de.felite.controller.status

import de.felite.controller.GameController
import de.felite.controller.status.GameStateString.GameState
import de.felite.util.ObserverCommand

case class NextCmdState(controller: GameController) extends CurrentState {
  val state: GameState = GameStateString.NEXT_CMD

  override def handle(): Unit = controller.notifyObservers(ObserverCommand.READCOMMAND)

  override def toString(): String = {
    GameStateString.message(state)
  }
}