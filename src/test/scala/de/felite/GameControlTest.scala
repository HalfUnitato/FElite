package de.felite

import de.felite.io.FileIO

class GameControlTest extends TestBaseClass {
  var gameControl: GameControl = new GameControl()
  "The GameControl" when {
    "Initialization" should {
      "not throw Exceptions " in {
        noException shouldBe thrownBy(gameControl.init())
      }
    }
    "Move troop" should {
      "succeed" in{

      }
    }
  }
}