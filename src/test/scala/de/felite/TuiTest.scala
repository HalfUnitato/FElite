package de.felite

import de.felite.io.Tui

class TuiTest extends TestBaseClass {
  val field = Field("C:\\Users\\Unitato\\Documents\\HTWG-Konstanz" +
    "\\3-Semester\\SoftwareEngineering" +
    "\\Tut\\FElite\\src\\fieldTest.txt")
  //  val ret = Tui.readCoordiantes("pls insert coordiantes x:y")
//  for (x <- field.getField()) {
//    for (y <- x)
//      print(y)
//    println()
//  }

  "The Tui" when {
    "print Field" should {
      "doesn't throw an ERROR" in {
        noException shouldBe thrownBy(Tui.printField(field))
      }
    }
  }

}
