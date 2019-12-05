package de.felite.controller.status

import de.felite.controller.GameController
import de.felite.controller.status.GameStateString.GameState
import de.felite.util.ObserverCommand

case class P1InitState(controller:GameController) extends CurrentState {
  val state:GameState = GameStateString.P1_INI

  override def handle(): Unit =
    controller.notifyObservers(ObserverCommand.READSTRING)

  override def toString(): String = {
    GameStateString.message(state)
  }
}
