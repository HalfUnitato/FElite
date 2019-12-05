package de.felite.controller

import de.felite.TestBaseClass
import de.felite.controller.status._
import de.felite.model.Field
import de.felite.model.entity.figure.Soldier
import de.felite.model.entity.obstacle.Grass
import de.felite.util.ReturnValues
class StatusTest extends TestBaseClass {
  var controller: GameController = new GameController
  controller.init()

  "State" when {
    "state" should {
      "work" in {
        State.gameState = EndState(controller)
        State.gameState.handle shouldBe State.gameState.handle
        State.gameState.toString() shouldBe State.gameState.toString()

        State.gameState = InitState(controller)
        State.gameState.handle shouldBe State.gameState.handle
        State.gameState.toString() shouldBe State.gameState.toString()

        State.gameState = NextCmdState(controller)
        State.gameState.handle shouldBe State.gameState.handle
        State.gameState.toString() shouldBe State.gameState.toString()

        State.gameState = P1InitState(controller)
        State.gameState.handle shouldBe State.gameState.handle
        State.gameState.toString() shouldBe State.gameState.toString()

        State.gameState = P2InitState(controller)
        State.gameState.handle shouldBe State.gameState.handle
        State.gameState.toString() shouldBe State.gameState.toString()

        State.gameState = P1State(controller)
        State.gameState.handle shouldBe State.gameState.handle
        State.gameState.toString() shouldBe State.gameState.toString()

        State.gameState = P2State(controller)
        State.gameState.handle shouldBe State.gameState.handle
        State.gameState.toString() shouldBe State.gameState.toString()

        State.gameState = PrintFieldState(controller)
        State.gameState.handle shouldBe State.gameState.handle
        State.gameState.toString() shouldBe State.gameState.toString()

        State.gameState = QuitState(controller)
        State.gameState.handle shouldBe State.gameState.handle
        State.gameState.toString() shouldBe State.gameState.toString()
      }
    }
  }
}