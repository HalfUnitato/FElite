package de.felite

import de.felite.io.Tui

class TuiTest extends TestBaseClass {
  val field = Field("src\\fieldTest.txt")
  var fieldString: String = Tui.createFieldString(field)

  "The Tui" when {
    "print String" should {
      "not throw an ERROR when printing the fieldString" in {
        noException shouldBe thrownBy(Tui.printString(fieldString))
      }
    }
    "printing the help" should {
      "not throw an ERROR" in {
        noException shouldBe thrownBy(Tui.printHelp())
      }
    }
  }

}
