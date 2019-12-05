package de.felite.controller.status

import de.felite.controller.GameController
import de.felite.controller.status.GameStateString.GameState
import de.felite.util.ObserverCommand

case class InitState(controller:GameController) extends CurrentState {
  val state:GameState = GameStateString.INIT

  override def handle(): Unit = controller.notifyObservers(ObserverCommand.PRINTSTRING)

  override def toString(): String = {
    GameStateString.message(state)
  }
}
