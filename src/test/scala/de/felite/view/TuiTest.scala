package de.felite.view

import com.google.inject.Guice
import de.felite.controller.GameControllerInterface
import de.felite.{FEliteModule, TestBaseClass}

class TuiTest extends TestBaseClass {
  val injector = Guice.createInjector(new FEliteModule)
  val controller = injector.getInstance(classOf[GameControllerInterface])
  //controller.init()
  val tui: Tui = new Tui(controller)
  "The Tui" when {
    controller.init()
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
        tui.playerTurn("undo") shouldBe true
      }
      "redo move" in {
        tui.playerTurn("redo") shouldBe true
      }
      "print the field" in {
        tui.playerTurn("p") shouldBe true
      }
      "print the help" in {
        tui.playerTurn("help") shouldBe true
      }
      /*"cancel" in {
        tui.playerTurn("cancel")
        GameController.gameState shouldBe GameState.Cancel
      }*/
      "execute the command" in {
        //controller.switchPlayer()
        tui.playerTurn("0 0 1 1") shouldBe true
        tui.playerTurn("1 0 1 2") shouldBe false
        tui.playerTurn("-1 0 0 1") shouldBe false
        tui.playerTurn("0 0 9 1") shouldBe false
        tui.playerTurn("0 0 20 1") shouldBe false
        tui.playerTurn("O 0 20 1") shouldBe false
        tui.playerTurn("0 20 1") shouldBe false
      }
      "end" in {
        tui.playerTurn("end") shouldBe true
      }
    }
    /*"update" when {
      "ObserverCommand" should {
        "be PrintString" in {
          GameController.printString = "Marin is doof"
          tui.update(ObserverCommand.PRINTSTRING) shouldBe true
        }
        "be ReadCommand" in {
          GameController.cmdStr = "sickPWftw"
          tui.update(ObserverCommand.READCOMMAND) shouldBe false
        }
      }
    }*/
  }
}