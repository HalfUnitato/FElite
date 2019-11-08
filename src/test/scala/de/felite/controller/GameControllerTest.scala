package de.felite.controller

import de.felite.TestBaseClass
import de.felite.model.Player
import de.felite.util.ReturnValues


class GameControllerTest extends TestBaseClass {
 /* val playerOne = Player("Marin")
  val playerTwo = Player("Lukas")
  GameControl.init(playerOne,playerTwo)

  "The GameControl" when {
    "Initialization" should {
      "not throw Exceptions " in {
        noException shouldBe thrownBy(GameControl.init(playerOne, playerTwo))
      }
    }
    "isEnd" should {
      "fail" in {
        !GameControl.isEnd(playerOne, playerOne) shouldBe true
      }
    }
    "playerTurn" should {
      "print the help" in {
        GameControl.playerTurn(playerOne, "help") shouldBe ReturnValues.VALID
      }
      "quit" in {
        GameControl.playerTurn(playerOne, "quit") shouldBe ReturnValues.QUIT
      }
      "cancel" in {
        GameControl.playerTurn(playerOne, "cancel") shouldBe ReturnValues.CANCEL
      }
      "end" in {
        GameControl.playerTurn(playerOne, "end") shouldBe ReturnValues.END
      }
      "execute the command" in {
        GameControl.playerTurn(playerOne, "0 0 m 0 1") shouldBe ReturnValues.VALID
        GameControl.playerTurn(playerOne, "0 0 a 0 1") shouldBe ReturnValues.VALID
        GameControl.playerTurn(playerTwo, "-1 0 m 0 1") shouldBe ReturnValues.INVALID
        GameControl.playerTurn(playerTwo, "0 0 m 9 1") shouldBe ReturnValues.INVALID
        GameControl.playerTurn(playerTwo, "0 0 c 20 1") shouldBe ReturnValues.INVALID
        GameControl.playerTurn(playerOne, "O 0 m 20 1") shouldBe ReturnValues.INVALID
        GameControl.playerTurn(playerOne, "0 m 20 1") shouldBe ReturnValues.INVALID

      }
    }
  }*/
}