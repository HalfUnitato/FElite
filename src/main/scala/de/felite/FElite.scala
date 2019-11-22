package de.felite

import de.felite.controller.{GameController, GameState}
import de.felite.model.{Field, Player}
import de.felite.util.{ObserverCommand, ReturnValues}
import de.felite.view.Tui
import de.felite.controller.GameState._

object FElite {

  private val fieldSrc = "src\\fieldTest.txt"
  val controller = new GameController(Field(fieldSrc, 3)) // second param is scal value
  val tui = new Tui(controller)


  def Main(args: Array[String]): Unit = {

    println("Welcome to Fire Emblem lite")

    controller.init()

    //Main read input passes it to tui?
    //what about ObserverCommand.READCOMMAND
    while (!controller.isEnd && controller.gameState != QUIT) {
      controller.gameState = QUIT

      while (controller.gameState != END && controller.gameState != QUIT) {
        val command = scala.io.StdIn.readLine()
        controller.nextPlayerMove(command)
      }
      // switch payer after each turn
      if (!controller.isEnd && controller.gameState != QUIT)
        controller.switchPlayer()
    }
  }
}
