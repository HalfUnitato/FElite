package de.felite.controller.state.game

import GameStateString.GameState
import de.felite.controller.GameControllerInterface
import de.felite.controller.state.player.PlayerState
import de.felite.util.ObserverCommand
import de.felite.util.ObserverCommand.PRINTSTRING

case class EndState(controller: GameControllerInterface) extends CurrentState {
  val state: GameState = GameStateString.END

  override def handle(): GameState = {
    controller.state.gameState = EndState(controller)
    controller.notifyObservers(ObserverCommand.PRINTSTRING)
    controller.nextTurn()
    PlayerState.handle(controller)
    controller.notifyObservers(PRINTSTRING)
    state
  }

  override def toString: String = {
    GameStateString.message(state)
  }
}
