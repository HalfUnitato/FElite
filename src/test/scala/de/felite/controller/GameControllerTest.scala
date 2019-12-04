package de.felite.controller

import de.felite.model.{Field, Player}
import de.felite.util.{FileIO, ReturnValues}
import de.felite.{TestBaseClass, util}

class controllerTest extends TestBaseClass {
  val playerOne = Player("Marin", Console.BLUE)
  val playerTwo = Player("Lukas", Console.RED)
  val controller:GameController = new GameController()

  "The GameControl" when {
    "Initialization" should {
      "not throw Exceptions " in {
        noException shouldBe thrownBy(controller.init())
      }
    }
    "player info" when {
      "get name" should {
        "not fail" in {
          controller.getPlayerName == "Marin"
        }
      }
    }
    "isEnd" should {
      "fail" in {
        controller.isEnd shouldBe false
      }
    }
    "troop actions" should {
      "move not fail" in {
        controller.move((0, 0), (0, 1)) shouldBe util.ReturnValues.VALID
      }
      "undo not fail" in {
        controller.move((0, 0), (0, 1))
        controller.undo
      }
      "redo not fail" in {
        controller.move((0, 0), (0, 1))
        controller.undo
        controller.redo
      }
      "move fail" in {
        controller.move((0, 5), (0, 1)) shouldBe ReturnValues.INVALID
        controller.move((1, 1), (0, 1)) shouldBe ReturnValues.INVALID
      }
       "attack not fail" in {
         controller.attack((0, 0), (0, 1)) shouldBe ReturnValues.VALID
       }
    }
    "switch to player" should {
      "not fail" in {
        controller.getPlayerName == "Marin"
        controller.switchPlayer()
        controller.getPlayerName == "Lukas"
        controller.switchPlayer()
        controller.getPlayerName == "Marin"
      }
      "next layer move" should {
        "not fail" in {
         //noException shouldBe thrownBy(controller.nextPlayerMove("0 0 m 0 1"))
        }
      }
    }
  }
}