package de.felite

import de.felite.controller.GameController
import de.felite.model.{Field, Player}
import de.felite.util.{ObserverCommand, ReturnValues}
import de.felite.view.Tui

object FElite {

  private val fieldSrc = "src\\fieldTest.txt"
  val controller = new GameController(Field(fieldSrc, 3)) // second param is scal value
  val tui = new Tui(controller)


  def main(args: Array[String]): Unit = {

    println("Welcome to Fire Emblem lite")

    controller.init()

    //Main read input passes it to tui?
    //what about ObserverCommand.READCOMMAND
    while (!controller.isEnd && controller.gameState != ReturnValues.QUIT) {
      controller.gameState = ReturnValues.VALID

      while (controller.gameState != ReturnValues.END && controller.gameState != ReturnValues.QUIT) {
        val command = scala.io.StdIn.readLine()
        controller.nextPlayerMove(command)
      }
      // switch payer after each turn
      if (!controller.isEnd && controller.gameState != ReturnValues.QUIT)
        controller.switchPlayer()
    }
  }
}
