package de.felite.controller.status

import de.felite.controller.GameController
import de.felite.controller.status.GameStateString.GameState
import de.felite.util.ObserverCommand

case class P1State(controller:GameController) extends CurrentState {
  val state:GameState = GameStateString.P1

  override def handle(): Unit =
    controller.notifyObservers(ObserverCommand.PRINTSTRING)

  override def toString(): String = {
    controller.getPlayerName + GameStateString.message(state)
  }
}