package de.felite.view

import de.felite.TestBaseClass
import de.felite.controller.GameController
import de.felite.model.{Field, Player}
import de.felite.util.{ObserverCommand, ReturnValues}

class TuiTest extends TestBaseClass {
  val controller = new GameController(Field("src\\fieldTest.txt", 3)) //scal should match testing field specified in Field
  val tui: Tui = new Tui(controller)

  "The Tui" when {
    controller.init(1)
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
    "playerTurn" should {
      "print the field" in {
        tui.playerTurn("p") shouldBe ReturnValues.VALID
      }
      "print the help" in {
        tui.playerTurn("help" )
        controller.gameState shouldBe ReturnValues.VALID
      }
      "quit" in {
        tui.playerTurn("quit")
        controller.gameState shouldBe ReturnValues.QUIT
      }
      "cancel" in {
        tui.playerTurn("cancel")
        controller.gameState shouldBe ReturnValues.CANCEL
      }
      "end" in {
        tui.playerTurn("end")
        controller.gameState shouldBe ReturnValues.END
      }
      "execute the command" in {
        tui.playerTurn("0 0 m 0 1") shouldBe ReturnValues.VALID
        tui.playerTurn("0 0 a 0 1") shouldBe ReturnValues.VALID
        tui.playerTurn("-1 0 m 0 1") shouldBe ReturnValues.INVALID
        tui.playerTurn("0 0 m 9 1") shouldBe ReturnValues.INVALID
        tui.playerTurn("0 0 c 20 1") shouldBe ReturnValues.INVALID
        tui.playerTurn("O 0 m 20 1") shouldBe ReturnValues.INVALID
        tui.playerTurn("0 m 20 1") shouldBe ReturnValues.INVALID
      }
    }
    /*"update" when {
      "ObserverCommand" should {
        "be PrintString" in {
          controller.printString = "Marin is doof"
          tui.update(ObserverCommand.PRINTSTRING) shouldBe ReturnValues.VALID
        }
        "be ReadCommand" in {
          controller.cmdStr = "sickPWftw"
          tui.update(ObserverCommand.READCOMMAND) shouldBe ReturnValues.INVALID
        }
      }
    }*/
  }
}
