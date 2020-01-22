package de.felite.controller

import de.felite.TestBaseClass
import de.felite.controller.component.controllerBaseImpl.GameController
import de.felite.model.{Field, Player}

class GameControllerBaseImplTest extends TestBaseClass {
  val playerOne: Player = Player("Marin", Console.BLUE,1)
  val playerTwo: Player = Player("Lukas", Console.RED,2)
  val controller: GameControllerInterface = new GameController(new Field(3))

  "The GameControl" when {
    "Initialization" should {
      "init not fail " in {
        controller.init() shouldBe controller.init()
      }
    }
    "player info" when {
      "get name" should {
        "not fail" in {
          controller.getPlayerName == "Marin"
        }
      }
    }
    "troop actions" should {
      "attack" in {
        controller.btnStartCoord = (1, 0)
        controller.btnEndCoord = (1, 2)
        controller.doMove shouldBe false
      }
      "attack failed" in {
        controller.btnStartCoord = (1, 0)
        controller.btnEndCoord = (0, 0)
        controller.doMove shouldBe false
      }
      "attack not fail" in {
        controller.btnStartCoord = (1, 0)
        controller.btnEndCoord = (2, 3)
        controller.doMove shouldBe true
        controller.doMove shouldBe true
        controller.doMove shouldBe true
        controller.doMove shouldBe true
        controller.doMove shouldBe true
        controller.doMove shouldBe true
      }
      "move not fail" in {
        controller.btnStartCoord = (0, 0)
        controller.btnEndCoord = (1, 1)
        controller.doMove shouldBe true
        controller.btnStartCoord = (1, 2)
        controller.btnEndCoord = (0, 1)
        controller.doMove shouldBe false
      }
      "undo not fail" in {
        controller.btnStartCoord = (0, 0)
        controller.btnEndCoord = (0, 1)
        controller.doMove()
        controller.undo()
      }
      "nextTurn not fail" in {
        controller.btnStartCoord = (0, 0)
        controller.btnEndCoord = (0, 1)
        controller.doMove()
        controller.nextTurn()
      }
      "redo not fail" in {
        controller.btnStartCoord = (0, 0)
        controller.btnEndCoord = (0, 1)
        controller.doMove()
        controller.undo()
        controller.redo()
      }
      "move fail" in {
        controller.btnStartCoord = (0, 5)
        controller.btnEndCoord = (0, 1)
        controller.doMove shouldBe false

        controller.btnStartCoord = (1, 1)
        controller.btnEndCoord = (0, 1)
        controller.doMove shouldBe false
      }
    }
  }
}