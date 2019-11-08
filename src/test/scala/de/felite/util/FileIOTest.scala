package de.felite.util

import de.felite.TestBaseClass

class FileIOTest extends TestBaseClass {
  "A FileIO" when {
    var field: Array[Array[String]] = null
    "readFromFIle" should {
      "is not null " in {
        noException shouldBe thrownBy(FileIO.readFromFile("src\\fieldTest.txt"))

      }
    }
  }
}