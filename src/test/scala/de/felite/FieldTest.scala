package de.felite

class FieldTest extends TestBaseClass {
  "A Field" when {
    val testField = Field("C:\\Users\\Unitato\\Documents\\HTWG-Konstanz\\3-Semester\\SoftwareEngineering\\Tut\\FElite\\src\\fieldTest.txt")
    "do move" should {
      "move is valide" in {
        testField.doMove(0, 0, 0, 5) shouldBe true
      }
      "move throwns no Exception for Index -1" in {
       testField.doMove(-1, 0,0,0) shouldBe false
      }
      "move throwns no Exception for Index 10" in {
        testField.doMove(-1, 0,10,0) shouldBe false
      }
    }
  }
}