package de.felite.controller.state.game

import GameStateString.GameState

trait CurrentState {
  val state:GameState
  def handle():GameState
  def toString:String
}
