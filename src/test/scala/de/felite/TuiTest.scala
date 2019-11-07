package de.felite

import de.felite.io.Tui

class TuiTest extends TestBaseClass {
  val field = Field("src\\fieldTest.txt")

  "The Tui" when {
    "print String" should {
      "not fail" in {
        noException shouldBe thrownBy(Tui.printString("Halloo"))
      }
    }
    /*"readLine" should {
      "not fail" in {
        an[Exception] shouldBe thrownBy(Tui.readLine("type in command"))
      }
    }*/
    "print Field" should {
      "doesn't throw an ERROR" in {
        noException shouldBe thrownBy(Tui.printField(field))
      }
    }
  }

}
