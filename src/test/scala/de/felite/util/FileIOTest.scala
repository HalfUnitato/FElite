package de.felite.util

import de.felite.TestBaseClass
import de.felite.model.entity.Entity

class FileIOTest extends TestBaseClass {
  "A FileIO" when {
    "setting the scale" in {
      noException shouldBe thrownBy(FileIO.setScal(3))
    }
    "getting the scale" in {
      FileIO.getScal should be (3)
    }
    "readFromFIle" should {
      "not throw an Exception " in {
        noException shouldBe thrownBy(FileIO.readFromFile())
      }
      "return a field" in {
        val field = FileIO.readFromFile()
        field.isInstanceOf[Array[Array[Entity]]] shouldBe true
      }
    }
  }
}