package de.felite.controller.status

import de.felite.controller.{GameController, GameControllerInterface}

object PlayerState {
  var state: GameStateString.Value = GameStateString.P1
  var controller: GameControllerInterface = _

  def handle(gameController:GameControllerInterface): GameStateString.Value = {
    controller = gameController
    state match {
      case GameStateString.P1 => switchToPlayer2
      case GameStateString.P2 => switchToPlayer1
      case _ =>
    }
    state
  }

  def switchToPlayer1: GameStateString.Value = {
    State.gameState = P1State(controller)
    state = GameStateString.P1
    controller.currentPlayer = controller.player1
    state
  }

  def switchToPlayer2: GameStateString.Value = {
    State.gameState = P2State(controller)
    state = GameStateString.P2
    controller.currentPlayer = controller.player2
    state
  }
}