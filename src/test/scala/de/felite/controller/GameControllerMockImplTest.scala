package de.felite.controller

import de.felite.controller.component.controllerImpl.controllerStubImpl.GameController
import de.felite.model.{Field, Player}
import de.felite.util.FileIO
import de.felite.{TestBaseClass, util}

class GameControllerMockImplTest extends TestBaseClass {

  val playerOne: Player = Player("Marin", Console.BLUE)
  val playerTwo: Player = Player("Lukas", Console.RED)
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

