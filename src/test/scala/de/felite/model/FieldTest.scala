package de.felite.model

import de.felite.TestBaseClass
import de.felite.model.entity.figure.Soldier

class FieldTest extends TestBaseClass {
  "A Field" when {
    val testField = Field
    val player = Player()

    "get Field" should {
      "not be null" in {
        testField.getField shouldBe testField.getField
      }
    }
    "Field toString" should {
      "not be empty" in {
        testField.toString should not be empty
      }
    }
    "performing a move" should {
      "be valid (currently it's invalid)" in {
        testField.setCell(DefEntity, 0, 3) shouldBe false //this should be valid
      }
      "throw no Exception for Index -1" in {
        testField.setCell(DefEntity, -1, 0) shouldBe false
      }
      "throw no Exception for Index 10" in {
        testField.setCell(DefEntity, 10, 0) shouldBe false
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
        testField.setCell(sold, x, y) shouldBe true
      }
      "throw no Exception for Index -1" in {
        x = -1
        y = 1
        testField.setCell(sold, x, y) shouldBe false
      }
      "set throwns no Exception for Index 10" in {
        x = 10
        y = 1
        testField.setCell(sold, x, y) shouldBe false
      }
    }
  }
}