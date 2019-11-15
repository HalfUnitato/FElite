package de.felite.controller

import de.felite.model.{Field, Player}
import de.felite.util.{FileIO, ReturnValues}
import de.felite.{TestBaseClass, util}

class GameControllerTest extends TestBaseClass {
  val controller: GameController = new GameController(Field("src\\fieldTest.txt", 5))

  val playerOne = Player("Marin", Console.BLUE)
  val playerTwo = Player("Lukas", Console.RED)

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
        controller.doMove((0,0), (0, 1)) shouldBe util.ReturnValues.VALID
      }
      "move fail" in {
        controller.doMove((0, 5), (0, 1)) shouldBe ReturnValues.INVALID
      }
     /* "attack not fail" in {
        controller.doAttack((0, 5), (0, 1)) shouldBe ReturnValues.VALID
      }*/
    }
    "switch to Player" should {
      "not fail" in {
        controller.getPlayerName == "Marin"
        controller.switchPlayer()
        controller.getPlayerName == "Lukas"
        controller.switchPlayer()
        controller.getPlayerName == "Marin"
      }
    }
  }
}