package de.felite.controller

import com.google.inject.Guice
import de.felite.model.Player
import de.felite.{FEliteModule, TestBaseClass}

class GameControllerMockImplTest extends TestBaseClass {

  val playerOne: Player = Player("Marin", Console.BLUE,1)
  val playerTwo: Player = Player("Lukas", Console.RED,2)

  val injector = Guice.createInjector(new FEliteModule)
  val controller = injector.getInstance(classOf[GameControllerInterface])
  controller.init()

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
        controller.doMove shouldBe false
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

