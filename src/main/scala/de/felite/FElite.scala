package de.felite

import de.felite.controller.GameController
import de.felite.model.{Field, Player}
import de.felite.util.{ObserverCommand, ReturnValues}
import de.felite.view.Tui
import de.felite.controller.GameState._

object FElite {

  private val fieldSrc = "src\\fieldTest.txt"
  //val controller = new GameController // second param is scal value
  val tui = new Tui()


  def main(args: Array[String]): Unit = {

    println("Welcome to Fire Emblem lite")

    GameController.init()

    //Main read input passes it to tui?
    //what about ObserverCommand.READCOMMAND
    while (!GameController.isEnd && GameController.gameState != QUIT) {

      while (GameController.gameState != END && GameController.gameState != QUIT) {
        GameController.nextPlayerMove()
      }
    }
  }
}
