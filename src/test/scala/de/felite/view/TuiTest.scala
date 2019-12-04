package de.felite.view

import de.felite.TestBaseClass
import de.felite.controller.{GameController, GameState}
import de.felite.model.{Field, Player}
import de.felite.util.{ObserverCommand, ReturnValues}

import scala.util.control.Exception

class TuiTest extends TestBaseClass {
  val controller:GameController = new GameController()
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
      "undo move" in {
        tui.playerTurn("undo") shouldBe ReturnValues.VALID
      }
      "redo move" in {
        tui.playerTurn("redo") shouldBe ReturnValues.VALID
      }
      "print the field" in {
        tui.playerTurn("p") shouldBe ReturnValues.VALID
      }
      "print the help" in {
        tui.playerTurn("help") shouldBe ReturnValues.VALID
      }
      "quit" in {
        tui.playerTurn("quit")
        controller.gameState shouldBe GameState.QUIT
      }
      /*"cancel" in {
        tui.playerTurn("cancel")
        GameController.gameState shouldBe GameState.Cancel
      }*/
      "execute the command" in {
        //controller.switchPlayer()
        tui.playerTurn("0 0 m 0 1") shouldBe ReturnValues.VALID
        tui.playerTurn("0 0 a 0 1") shouldBe ReturnValues.VALID
        tui.playerTurn("-1 0 m 0 1") shouldBe ReturnValues.INVALID
        tui.playerTurn("0 0 m 9 1") shouldBe ReturnValues.INVALID
        tui.playerTurn("0 0 c 20 1") shouldBe ReturnValues.INVALID
        an[NumberFormatException] shouldBe thrownBy(tui.playerTurn("O 0 m 20 1"))
        tui.playerTurn("0 m 20 1") shouldBe ReturnValues.INVALID
      }
      "end" in {
        tui.playerTurn("end") shouldBe ReturnValues.VALID
      }
    }
    /*"update" when {
      "ObserverCommand" should {
        "be PrintString" in {
          GameController.printString = "Marin is doof"
          tui.update(ObserverCommand.PRINTSTRING) shouldBe ReturnValues.VALID
        }
        "be ReadCommand" in {
          GameController.cmdStr = "sickPWftw"
          tui.update(ObserverCommand.READCOMMAND) shouldBe ReturnValues.INVALID
        }
      }
    }*/
  }
}