package de.felite.controller.status

import de.felite.controller.GameController

object PlayerState {
  var state = GameStateString.P1
  var controller: GameController = _

  def handle(gameController:GameController) = {
    controller = gameController
    state match {
      case GameStateString.P1 => switchToPlayer2
      case GameStateString.P2 => switchToPlayer1
      case _ =>
    }
    state
  }

  def switchToPlayer1 = {
    State.gameState = new P1State(controller)
    state = GameStateString.P1
    controller.currentPlayer = controller.player1
    state
  }

  def switchToPlayer2 = {
    State.gameState = new P2State(controller)
    state = GameStateString.P2
    controller.currentPlayer = controller.player2
    state
  }
}