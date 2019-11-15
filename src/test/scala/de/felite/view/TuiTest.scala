package de.felite.view

import de.felite.TestBaseClass
import de.felite.controller.GameController
import de.felite.model.Field
import de.felite.util.ReturnValues

class TuiTest extends TestBaseClass {
  val tui: Tui = new Tui(new GameController(Field("src\\fieldTest.txt", 42))) //scal is unimported while testing tui

  "The Tui" when {
    "print String" should {
      "not throw an ERROR when printing the fieldString" in {
        noException shouldBe thrownBy(tui.printString("t3st"))
      }
    }
    "printing the help" should {
      "not throw an ERROR" in {
        noException shouldBe thrownBy(tui.printHelp())
      }
    }
    //playerTurn testing goes here
    //How to use input? where to read input?
    /*"playerTurn" should {
      "print the help" in {
        tui.playerTurn(playerOne, "help") shouldBe ReturnValues.VALID
      }
      "quit" in {
        tui.playerTurn(playerOne, "quit") shouldBe ReturnValues.QUIT
      }
      "cancel" in {
        tui.playerTurn(playerOne, "cancel") shouldBe ReturnValues.CANCEL
      }
      "end" in {
        tui.playerTurn(playerOne, "end") shouldBe ReturnValues.END
      }
      "execute the command" in {
        tui.playerTurn("0 0 m 0 1") shouldBe ReturnValues.VALID
        tui.playerTurn(playerOne, "0 0 a 0 1") shouldBe ReturnValues.VALID
        tui.playerTurn(playerTwo, "-1 0 m 0 1") shouldBe ReturnValues.INVALID
        tui.playerTurn(playerTwo, "0 0 m 9 1") shouldBe ReturnValues.INVALID
        tui.playerTurn(playerTwo, "0 0 c 20 1") shouldBe ReturnValues.INVALID
        tui.playerTurn(playerOne, "O 0 m 20 1") shouldBe ReturnValues.INVALID
        tui.playerTurn(playerOne, "0 m 20 1") shouldBe ReturnValues.INVALID

      }
    }*/
  }
}
