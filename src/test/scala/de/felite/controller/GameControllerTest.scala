package de.felite.controller

import de.felite.controller.component.controllerImpl.controllerBaseImpl.GameController
import de.felite.model.{Field, Player}
import de.felite.util.FileIO
import de.felite.{TestBaseClass, util}

class GameControllerTest extends TestBaseClass {
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
          controller.getPlayerName == "Marin"
        }
      }
    }
    "troop actions" should {
      "attack" in {
        controller.btnStartCoord = (1, 0)
        controller.btnEndCoord = (1, 2)
        controller.doMove shouldBe true
      }
      "attack failed" in {
        controller.btnStartCoord = (1, 0)
        controller.btnEndCoord = (0, 0)
        controller.doMove shouldBe false
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
      //       "attack not fail" in {
      //         controller.attack((0, 0), (0, 1)) shouldBe ReturnValues.VALID
      //       }
    }
    /*"switch to player" should {
      "not fail" in {
        controller.getPlayerName == "Marin"
        controller.switchPlayer()
        controller.getPlayerName == "Lukas"
        controller.switchPlayer()
        controller.getPlayerName == "Marin"
      }
      "next layer move" should {
        "not fail" in {
         noException shouldBe thrownBy(controller.nextPlayerMove("0 0 m 0 1"))
        }
      }
    } */
  }
}