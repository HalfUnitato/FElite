package de.felite.controller.status

import de.felite.controller.GameController
import de.felite.controller.status.GameStateString.GameState
import de.felite.util.ObserverCommand
import de.felite.util.ObserverCommand.PRINTSTRING

case class EndState(controller: GameController) extends CurrentState {
  val state: GameState = GameStateString.END

  override def handle(): Unit = {
    State.gameState = new EndState(controller)
    controller.notifyObservers(ObserverCommand.PRINTSTRING)
    controller.undoManager.reset
    PlayerState.handle(controller)
    controller.notifyObservers(PRINTSTRING)
  }

  override def toString(): String = {
    GameStateString.message(state)
  }
}
