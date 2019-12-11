package de.felite.util

import de.felite.TestBaseClass
import de.felite.model.entity.Entity

class FileIOTest extends TestBaseClass {
  "A FileIO" when {
    "setting the scale" in {
      FileIO.setScal(3) shouldBe true
    }
    "getting the scale" in {
      FileIO.getScal should be (3)
    }
    "readFromFIle" should {
      "valid" in {
        FileIO.readFromFile() shouldBe FileIO.readFromFile()
      }
      "return a field" in {
        val field = FileIO.readFromFile()
        field.isInstanceOf[Array[Array[Option[Entity]]]] shouldBe true
      }
    }
  }
}