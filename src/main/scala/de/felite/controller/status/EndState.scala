package de.felite.controller.status

import de.felite.controller.{GameController, GameControllerInterface}
import de.felite.controller.status.GameStateString.GameState
import de.felite.util.ObserverCommand
import de.felite.util.ObserverCommand.PRINTSTRING

case class EndState(controller: GameControllerInterface) extends CurrentState {
  val state: GameState = GameStateString.END

  override def handle(): GameState = {
    State.gameState = EndState(controller)
    controller.notifyObservers(ObserverCommand.PRINTSTRING)
    controller.undoManager.reset()
    PlayerState.handle(controller)
    controller.notifyObservers(PRINTSTRING)
    state
  }

  override def toString(): String = {
    GameStateString.message(state)
  }
}
