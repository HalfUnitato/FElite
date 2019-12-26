package de.felite.util

import de.felite.TestBaseClass
import de.felite.model.Entity

class FileIOTest extends TestBaseClass {
  val scal: Int = 3
  val fileName: String = "src/fieldbase.txt"
  "A FileIO" when {
    "getting the scale" in {
      FileIO.getScal should be(scal)
    }
    "readFromFIle" should {
      "valid" in {
        FileIO.readFromFile(scal, fileName) shouldBe FileIO.readFromFile(scal, fileName)
      }
      "return a field" in {
        val field = FileIO.readFromFile(scal, fileName)
        field.isInstanceOf[Array[Array[Option[Entity]]]] shouldBe true
      }
    }
  }
}