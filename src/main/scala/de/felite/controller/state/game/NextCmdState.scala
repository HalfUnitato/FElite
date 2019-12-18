package de.felite.controller.state.game

import de.felite.controller.GameControllerInterface
import de.felite.controller.state.game.GameStateString.GameState
import de.felite.util.ObserverCommand

case class NextCmdState(controller: GameControllerInterface) extends CurrentState {
  val state: GameState = GameStateString.NEXT_CMD

  override def handle(): GameState = {
    controller.notifyObservers(ObserverCommand.READCOMMAND)
    state
  }

  override def toString: String = {
    GameStateString.message(state)
  }
}
