package de.felite.controller

import com.google.inject.Guice
import de.felite.model.Player
import de.felite.{FEliteModule, TestBaseClass}
import de.felite.controller.component.controllerStubImpl.GameController

class GameControllerStubImplTest extends TestBaseClass {

  val playerOne: Player = Player("Marin", Console.BLUE, 1)
  val playerTwo: Player = Player("Lukas", Console.RED, 2)

  val controller = new GameController
  controller.init()

  "The GameControl" when {
    "Initialization" should {
      "init not fail " in {
        controller.init() shouldBe controller.init()
      }
    }
    "player info" when {
      "get name" should {
        "not fail" in {
          controller.getPlayerName == "Ike"
        }
      }
    }
    "accessing variables" should {
      "printString be empty" in {
        controller.printString = ""
        controller.printString.equals("")
      }
      "readString be empty" in {
        controller.readString = ""
        controller.readString.equals("")
      }
      "cmdStr be empty" in {
        controller.cmdStr = ""
        controller.cmdStr.equals("")
      }
    }
    "call methods" should {
      "return empty Field" in {
        controller.fieldToString.equals("")
      }
      "return true" in {
        controller.doMove() should be
        true
      }
      "undo not fail" in {
        controller.undo()
      }
      "redo not fail" in {
        controller.redo()
      }
      "nextTurn not fail" in {
        controller.nextTurn()
      }
      "load not fail" in {
        controller.load(size=3)
      }
      "store not fail" in {
        controller.store()
      }
    }
  }
}

