package de.felite.controller.status

import de.felite.controller.{GameController, GameControllerInterface}
import de.felite.controller.status.GameStateString.GameState
import de.felite.util.ObserverCommand

case class PrintFieldState(controller:GameControllerInterface) extends CurrentState {
  val state:GameState = GameStateString.PRINT_FIELD

  override def handle(): GameState = {
    controller.notifyObservers(ObserverCommand.PRINTSTRING)
    state
  }


  override def toString(): String = {
    GameStateString.message(state) + "\n" + controller.FieldToString
  }
}
