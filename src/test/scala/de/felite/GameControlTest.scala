package de.felite

class GameControlTest extends TestBaseClass {
  var gameControl = GameControl
  val playerOne = new Player("Marin")
  val playerTwo = new Player("Lukas")
  "The GameControl" when {
    "Initialization" should {
      "not throw Exceptions " in {
        noException shouldBe thrownBy(gameControl.init(playerOne,playerTwo))
      }
    }
    "isEnd" should {
      "fail" in{
        !gameControl.isEnd(playerOne, playerOne)
      }
    }
    /*"printHelp" should {
      "not throw Exceptions" in{
        noException shouldBe thrownBy(gameControl.printHelp())
      }
    }*/
  }
}