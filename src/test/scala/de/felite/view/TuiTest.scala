package de.felite.view

import de.felite.TestBaseClass
import de.felite.controller.GameController
import de.felite.model.Field

class TuiTest extends TestBaseClass {
  val tui:Tui = new Tui(new GameController(new Field("src\\fieldTest.txt")))

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
  }
}
