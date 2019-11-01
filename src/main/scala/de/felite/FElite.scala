package de.felite

import de.felite.io.Tui

object FElite {
  def main(args: Array[String]): Unit = {
    GameControl.init()
    val playerOne = new Player("Marin");
    val playerTwo = new Player("Lukas");
    // run GAme for ever
    var currentPlayer = playerOne
    var currentRun = ReturnValues.VALID

    while (!GameControl.isEnd(playerOne, playerTwo) && currentRun != ReturnValues.QUIT ) {
      Tui.printString("------ " + currentPlayer.getPlayerName + "'s turn ------")
      currentRun = ReturnValues.VALID

      while (currentRun != ReturnValues.END){
        currentRun = GameControl.playerTurn(currentPlayer)
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
