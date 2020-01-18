package de.felite.model

import com.google.inject.{Guice, Injector}
import de.felite.controller.GameControllerInterface
import de.felite.{FEliteModule, TestBaseClass}

class FieldTest extends TestBaseClass {
  val injector: Injector = Guice.createInjector(new FEliteModule)
  val controller: GameControllerInterface = injector.getInstance(classOf[GameControllerInterface])
  controller.init()


  "A Field" when {
    val testField = controller.field
    val player = Player(_number = 1)

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
      "be valid " in {
        testField.setCell(ObstacleFactory('g'), 0, 3) shouldBe true //this should be valid
      }
      "throw no Exception for Index -1" in {
        testField.setCell(ObstacleFactory('g'), -1, 0) shouldBe false
      }
      "throw no Exception for Index 10" in {
        testField.setCell(ObstacleFactory('g'), 10, 0) shouldBe false
      }
    }
    "set Soldier" should {
      var x: Int = 1
      var y: Int = 1
      val sold: Troop = SoldierFactory('s', 1, player)

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