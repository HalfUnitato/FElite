package de.felite.GameController

import de.felite.controller.GameController
import de.felite.model.{Field, Player}
import de.felite.util.{FileIO, ReturnValues}
import de.felite.{TestBaseClass, util}

class GameControllerTest extends TestBaseClass {
  val playerOne = Player("Marin", Console.BLUE)
  val playerTwo = Player("Lukas", Console.RED)

  "The GameControl" when {
    "Initialization" should {
      "not throw Exceptions " in {
        noException shouldBe thrownBy(GameController.init())
      }
    }
    "player info" when {
      "get name" should {
        "not fail" in {
          GameController.getPlayerName == "Marin"
        }
      }
    }
    "isEnd" should {
      "fail" in {
        GameController.isEnd shouldBe false
      }
    }
    "troop actions" should {
      "move not fail" in {
        GameController.doMove((0, 0), (0, 1)) shouldBe util.ReturnValues.VALID
      }
      "move fail" in {
        GameController.doMove((0, 5), (0, 1)) shouldBe ReturnValues.INVALID
        GameController.doMove((1, 1), (0, 1)) shouldBe ReturnValues.INVALID
      }
       "attack not fail" in {
         GameController.doAttack((0, 0), (0, 1)) shouldBe ReturnValues.VALID
       }
    }
    "switch to player" should {
      "not fail" in {
        GameController.getPlayerName == "Marin"
        GameController.switchPlayer()
        GameController.getPlayerName == "Lukas"
        GameController.switchPlayer()
        GameController.getPlayerName == "Marin"
      }
      "next layer move" should {
        "not fail" in {
         //noException shouldBe thrownBy(GameController.nextPlayerMove("0 0 m 0 1"))
        }
      }
    }
  }
}