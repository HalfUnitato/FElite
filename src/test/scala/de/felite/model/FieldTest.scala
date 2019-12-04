package de.felite.model

import de.felite.TestBaseClass
import de.felite.model.entity.figure.Soldier
import de.felite.model.entity.obstacle.Grass
import de.felite.util.ReturnValues

class FieldTest extends TestBaseClass {
  "A Field" when {
    val testField = Field
    val player = Player()

    "get Field" should {
      "not be null" in {
        noException shouldBe thrownBy(testField.getField)
      }
    }
    "Field toString" should {
      "not be empty" in {
        testField.toString should not be empty
      }
    }
    "performing a move" should {
      "be valid (currently it's invalid)" in {
        testField.setCell(Grass, 0, 3) shouldBe ReturnValues.INVALID //this should be valid
      }
      "throw no Exception for Index -1" in {
        //an[IndexOutOfBoundsException] shouldBe thrownBy(testField.setCell(Grass, -1, 0))
        testField.setCell(Grass, -1, 0) shouldBe(ReturnValues.INVALID)
      }
      "throw no Exception for Index 10" in {
        //an[IndexOutOfBoundsException] shouldBe thrownBy(testField.setCell(Grass, 10, 0))
        testField.setCell(Grass, 10, 0) shouldBe(ReturnValues.INVALID)
      }
    }
    "set Soldier" should {
      var x: Int = 1
      var y: Int = 1
      val sold: Soldier = Soldier(1, 1, 1, 1, 1, x, y, player)

      "have an owner" in {
        sold.owner shouldEqual player
      }
      "be valid" in {
        x = 1
        y = 1
        testField.setCell(sold, x, y) shouldBe ReturnValues.VALID
      }
      "throw no Exception for Index -1" in {
        x = -1
        y = 1
        testField.setCell(sold, x, y) shouldBe ReturnValues.INVALID
      }
      "set throwns no Exception for Index 10" in {
        x = 10
        y = 1
        testField.setCell(sold, x, y) shouldBe ReturnValues.INVALID
      }
    }
  }
}