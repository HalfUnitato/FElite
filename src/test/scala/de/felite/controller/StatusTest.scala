package de.felite.controller

import de.felite.TestBaseClass
import de.felite.controller.status._
import de.felite.model.Field
import de.felite.model.entity.figure.Soldier
import de.felite.model.entity.obstacle.Grass
class StatusTest extends TestBaseClass {
  var controller: GameController = new GameController
  controller.init(1)

  "State" when {
    "state" should {
      "work" in {
        State.gameState = EndState(controller)
        State.gameState.handle shouldBe GameStateString.END
        State.gameState.toString() shouldBe controller.getPlayerName + GameStateString.message(GameStateString.P2)

        State.gameState = InitState(controller)
        State.gameState.handle shouldBe GameStateString.INIT
        State.gameState.toString() shouldBe GameStateString.message(GameStateString.INIT)

        State.gameState = NextCmdState(controller)
        State.gameState.handle shouldBe GameStateString.NEXT_CMD
        State.gameState.toString() shouldBe GameStateString.message(GameStateString.NEXT_CMD)

        State.gameState = P1InitState(controller)
        State.gameState.handle shouldBe GameStateString.P1_INI
        State.gameState.toString() shouldBe GameStateString.message(GameStateString.P1_INI)

        State.gameState = P2InitState(controller)
        State.gameState.handle shouldBe GameStateString.P2_INI
        State.gameState.toString() shouldBe GameStateString.message(GameStateString.P2_INI)

        State.gameState = P1State(controller)
        State.gameState.handle shouldBe GameStateString.P1
        State.gameState.toString() shouldBe controller.getPlayerName +  GameStateString.message(GameStateString.P1)

        State.gameState = P2State(controller)
        State.gameState.handle shouldBe GameStateString.P2
        State.gameState.toString() shouldBe controller.getPlayerName + GameStateString.message(GameStateString.P2)

        State.gameState = PrintFieldState(controller)
        State.gameState.handle shouldBe GameStateString.PRINT_FIELD
        State.gameState.toString() shouldBe GameStateString.message(GameStateString.PRINT_FIELD) + "\n" + controller.FieldToString

        State.gameState = QuitState(controller)
        State.gameState.handle shouldBe GameStateString.QUIT
        State.gameState.toString() shouldBe GameStateString.message(GameStateString.QUIT)
      }
    }
  }
}