package de.felite.controller

import com.google.inject.Guice
import de.felite.controller.state.game._
import de.felite.{FEliteModule, TestBaseClass}

class StatusTest extends TestBaseClass {
  val injector = Guice.createInjector(new FEliteModule)
  val controller = injector.getInstance(classOf[GameControllerInterface])
  controller.init()

  "State" when {
    "state" should {
      "work" in {
        controller.state.gameState = EndState(controller)
        controller.state.gameState.handle shouldBe GameStateString.END
        controller.state.gameState.toString shouldBe controller.getPlayerName + GameStateString.message(GameStateString.P2)

        controller.state.gameState = InitState(controller)
        controller.state.gameState.handle shouldBe GameStateString.INIT
        controller.state.gameState.toString shouldBe GameStateString.message(GameStateString.INIT)

        controller.state.gameState = NextCmdState(controller)
        controller.state.gameState.handle shouldBe GameStateString.NEXT_CMD
        controller.state.gameState.toString shouldBe GameStateString.message(GameStateString.NEXT_CMD)

        controller.state.gameState = P1InitState(controller)
        controller.state.gameState.handle shouldBe GameStateString.P1_INI
        controller.state.gameState.toString shouldBe GameStateString.message(GameStateString.P1_INI)

        controller.state.gameState = P2InitState(controller)
        controller.state.gameState.handle shouldBe GameStateString.P2_INI
        controller.state.gameState.toString shouldBe GameStateString.message(GameStateString.P2_INI)

        controller.state.gameState = P1State(controller)
        controller.state.gameState.handle shouldBe GameStateString.P1
        controller.state.gameState.toString shouldBe controller.getPlayerName + GameStateString.message(GameStateString.P1)

        controller.state.gameState = P2State(controller)
        controller.state.gameState.handle shouldBe GameStateString.P2
        controller.state.gameState.toString shouldBe controller.getPlayerName + GameStateString.message(GameStateString.P2)

        controller.state.gameState = PrintFieldState(controller)
        controller.state.gameState.handle shouldBe GameStateString.PRINT_FIELD
        controller.state.gameState.toString shouldBe GameStateString.message(GameStateString.PRINT_FIELD) + "\n" + controller.fieldToString
      }
    }
  }
}