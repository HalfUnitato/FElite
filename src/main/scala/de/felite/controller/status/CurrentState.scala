package de.felite.controller.status

import de.felite.controller.status.GameStateString.GameState

trait CurrentState {
  val state:GameState
  def handle
  def toString():String
}
