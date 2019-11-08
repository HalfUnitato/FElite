package de.felite

class FEliteTest extends TestBaseClass {
"A Game" when {
  val game = FElite
  "initialized" should {
    "have a TUI" in {
      game.tui should not be null
    }
    "have a controller" in {
      game.controller should not be null
    }
    "create two players" when {
      val input = "NukeDukem NaiserKero"
      game.tui.init(input)
      "Player One" should {
        "have a Name" in {
          game.controller.getPlayerName(1) shouldEqual "NukeDukem"
        }
      }
      "Player Two" should {
        "have a Name" in {
          game.controller.getPlayerName(2) shouldEqual "NaiserKero"
        }
      }
      "Using a wrong index" should {
        "return 'IllegalIndex' with pos <= 0" in {
          game.controller.getPlayerName(0) shouldEqual "IllegalIndex"
        }
        "return 'Illegalindex' with pos >= 3" in {
          game.controller.getPlayerName(3) shouldEqual "IllegalIndex"
        }
      }
    }
  }
}
}
