package de.felite

import de.felite.controller.GameController
import de.felite.model.{Field, Player}
import de.felite.util.{ObserverCommand, ReturnValues}
import de.felite.view.Tui

object FElite {

  private val fieldSrc = "src\\fieldTest.txt"
  val controller = new GameController(Field(fieldSrc))
  val tui = new Tui(controller)


  def main(args: Array[String]): Unit = {

    println("Welcome to Fire Emblem lite")

    controller.init()

    while (!controller.isEnd && controller.gameState != ReturnValues.QUIT) {
      controller.gameState = ReturnValues.VALID

      while (controller.gameState != ReturnValues.END && controller.gameState != ReturnValues.QUIT) {
        controller.nextPlayerMove()
      }
      // switch payer after each turn
      if (!controller.isEnd && controller.gameState != ReturnValues.QUIT)
        controller.switchPlayer()
    }
  }
}
