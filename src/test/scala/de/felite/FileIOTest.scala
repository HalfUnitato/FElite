package de.felite

import de.felite.io.FileIO

class FileIOTest extends TestBaseClass {
  "A FileIO" when {
    var field: Array[Array[String]] = null
    "readFromFIle" should {
      "is not null " in {
        noException shouldBe thrownBy(FileIO.readFromFile("C:\\Users\\Unitato\\Documents\\HTWG-Konstanz\\3-Semester\\SoftwareEngineering\\Tut\\FElite\\src\\fieldTest.txt"))

      }
    }
  }
}