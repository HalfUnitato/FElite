package de.felite.controller.status

import de.felite.controller.{GameController, GameControllerInterface}
import de.felite.controller.status.GameStateString.GameState
import de.felite.util.ObserverCommand

case class NextCmdState(controller: GameControllerInterface) extends CurrentState {
  val state: GameState = GameStateString.NEXT_CMD

  override def handle(): GameState = {
    controller.notifyObservers(ObserverCommand.READCOMMAND)
    state
  }

  override def toString(): String = {
    GameStateString.message(state)
  }
}
