package de.felite.controller.state.game

import de.felite.controller.GameControllerInterface
import de.felite.controller.state.game
import de.felite.controller.state.game.GameStateString.GameState
import de.felite.util.ObserverCommand

class WonState(controller:GameControllerInterface) extends CurrentState {
  val state:GameState = GameStateString.WON

  override def handle(): GameState = {
    controller.notifyObservers(ObserverCommand.PRINTSTRING)

    controller.state.gameState = QuitState(controller)
    controller.state.gameState.handle()

    state
  }

  override def toString: String = {
    controller.currentPlayer.getPlayerName + " " + GameStateString.message(state)
  }
}
