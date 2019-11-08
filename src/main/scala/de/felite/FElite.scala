package de.felite

import de.felite.controller.GameController
import de.felite.model.{Field, Player}
import de.felite.util.ReturnValues
import de.felite.view.Tui

object FElite {

  private val fieldSrc = "src\\fieldTest.txt"
  val controller = new GameController(Field(fieldSrc))
  val tui = new Tui(controller)
  controller.notifyObservers()

  def main(args: Array[String]): Unit = {
    /*val playerOne = Player("Marin")
    val playerTwo = Player("Lukas")
    GameControl.init(playerOne, playerTwo)
    // run Game for ever
    var currentPlayer = playerOne
    var currentRun = ReturnValues.VALID*/

    var input = ""

    println("Welcome to FElite")

    //initialisation
    println("------ Start of Initialisation ------")

    println("Name of Player One?")
    input = scala.io.StdIn.readLine()
    input += " "
    println("Name of Player Two?")
    input += scala.io.StdIn.readLine()

    tui.init(input)

    println("------ End of Initialisation ------")

    /*do {
      input = scala.io.StdIn.readLine()
      tui.playerTurn(input)
    } while (input != "end")*/

    /*while (!GameControl.isEnd(playerOne, playerTwo) && currentRun != ReturnValues.QUIT) {
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
    }*/
  }
}
