package de.felite

class GameControlTest extends TestBaseClass {
  val playerOne = new Player("Marin")
  val playerTwo = new Player("Lukas")
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
    /*"playerTurn" should {
      "be valid" in {
        GameControl.playerTurn(playerOne) shouldBe true
      }
    }*/
  }
}