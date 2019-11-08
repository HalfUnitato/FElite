package de.felite

import de.felite.io.Tui

object FElite {
  def main(args: Array[String]): Unit = {
    val playerOne = Player("Marin")
    val playerTwo = Player("Lukas")
    GameControl.init(playerOne, playerTwo)
    // run Game for ever
    var currentPlayer = playerOne
    var currentRun = ReturnValues.VALID

    while (!GameControl.isEnd(playerOne, playerTwo) && currentRun != ReturnValues.QUIT) {
      GameControl.playerTurn(currentPlayer,"p")
      Tui.printString("------ " + currentPlayer.getPlayerName + "'s turn ------")
      currentRun = ReturnValues.VALID

      while (currentRun != ReturnValues.END && currentRun != ReturnValues.QUIT) {
        currentRun = GameControl.playerTurn(currentPlayer, GameControl.getCommand(currentPlayer))

      }
      // switch payer after each turn
      currentPlayer =
        if (currentPlayer.equals(playerOne))
          playerTwo
        else
          playerOne
    }
  }
}
