package de.felite.controller

object PlayerState {
  var state = GameState.P1
  var controller: GameController = _

  def handle(gameController:GameController) = {
    controller = gameController
    state match {
      case GameState.P1 => switchToPlayer2
      case GameState.P2 => switchToPlayer1
      case _ =>
    }
    state
  }

  def switchToPlayer1 = {
    controller.gameState = GameState.P1
    state = GameState.P1
    controller.currentPlayer = controller.player1
  }

  def switchToPlayer2 = {
    controller.gameState = GameState.P2
    state = GameState.P2
    controller.currentPlayer = controller.player2
  }
}