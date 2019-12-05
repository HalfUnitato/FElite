package de.felite.controller.status

import de.felite.controller.GameController
import de.felite.controller.status.GameStateString.GameState
import de.felite.util.ObserverCommand

case class PrintFieldState(controller:GameController) extends CurrentState {
  val state:GameState = GameStateString.PRINT_FIELD

  override def handle(): Unit =
    controller.notifyObservers(ObserverCommand.PRINTSTRING)


  override def toString(): String = {
    GameStateString.message(state) + "\n" + controller.FieldToString
  }
}
