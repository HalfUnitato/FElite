package de.felite.controller

import de.felite.TestBaseClass
import de.felite.controller.component.controllerStubImpl.GameController
import de.felite.model.Player

class GameControllerMockImplTest extends TestBaseClass {

  val playerOne: Player = Player("Marin", Console.BLUE,1)
  val playerTwo: Player = Player("Lukas", Console.RED,2)
  val controller: GameControllerInterface = new GameController()

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
    "troop actions" should {
      "move not fail" in {
        controller.doMove shouldBe true
      }
      "undo not fail" in {
        controller.undo()
      }
      "redo not fail" in {
        controller.redo()
      }
      "nextTurn fail" in {
        controller.nextTurn()
      }
    }
  }
}

