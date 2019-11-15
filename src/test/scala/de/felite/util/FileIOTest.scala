package de.felite.util

import de.felite.TestBaseClass

class FileIOTest extends TestBaseClass {
  "A FileIO" when {
    "readFromFIle" should {
      "not throw an Exception " in {
        noException shouldBe thrownBy(FileIO.readFromFile("src\\fieldTest.txt"))
      }
    }
  }
}