package de.felite.controller

import de.felite.controller.GameController.{currentPlayer, gameState, player1, player2}
import de.felite.model.Player

object PlayerState {
  var state = GameState.P1

  def handle() = {
    state match {
      case GameState.P1 => switchToPlayer2
      case GameState.P2 => switchToPlayer1
      case _ =>
    }
    state
  }

  def switchToPlayer1 = {
    gameState = GameState.P1
    state = GameState.P1
    currentPlayer = player1
  }

  def switchToPlayer2 = {
    gameState = GameState.P2
    state = GameState.P2
    currentPlayer = player2
  }

}