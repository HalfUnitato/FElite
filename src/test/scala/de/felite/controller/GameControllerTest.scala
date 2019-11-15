package de.felite.controller

import de.felite.model.{Field, Player}
import de.felite.util.ReturnValues
import de.felite.{TestBaseClass, util}

class GameControllerTest extends TestBaseClass {
  val controller: GameController = new GameController(new Field("src\\fieldTest.txt"))

  val playerOne = Player("Marin", Console.BLUE)
  val playerTwo = Player("Lukas", Console.RED)
  //controller.init()

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
      "attack not fail" in {
        controller.doAttack((0, 5), (0, 1)) shouldBe ReturnValues.VALID
      }
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
    /*"playerTurn" should {
      "print the help" in {
        controller.playerTurn(playerOne, "help") shouldBe ReturnValues.VALID
      }
      "quit" in {
        controller.playerTurn(playerOne, "quit") shouldBe ReturnValues.QUIT
      }
      "cancel" in {
        controller.playerTurn(playerOne, "cancel") shouldBe ReturnValues.CANCEL
      }
      "end" in {
        controller.playerTurn(playerOne, "end") shouldBe ReturnValues.END
      }
      "execute the command" in {
        controller.playerTurn(playerOne, "0 0 m 0 1") shouldBe ReturnValues.VALID
        controller.playerTurn(playerOne, "0 0 a 0 1") shouldBe ReturnValues.VALID
        controller.playerTurn(playerTwo, "-1 0 m 0 1") shouldBe ReturnValues.INVALID
        controller.playerTurn(playerTwo, "0 0 m 9 1") shouldBe ReturnValues.INVALID
        controller.playerTurn(playerTwo, "0 0 c 20 1") shouldBe ReturnValues.INVALID
        controller.playerTurn(playerOne, "O 0 m 20 1") shouldBe ReturnValues.INVALID
        controller.playerTurn(playerOne, "0 m 20 1") shouldBe ReturnValues.INVALID

      }*/
  }
}