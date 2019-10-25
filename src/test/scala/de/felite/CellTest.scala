package de.felite

class CellTest extends TestBaseClass {
  "A Cell as Obstacle" when {
    "of Type Integer" should {
//      val integerCell = Cell(1)
//      "is not an String" in {
//        integerCell should not be null
//      }
    }
    "of Type Tree" should {
      val treeCell = Cell("Tree")
      "is not null" in{
        treeCell should not be null
      }
      "have walkthrough false" in {
        treeCell.cellPassage() should be(false)
      }

      /*"have destroyable false" in {
        treeCell.getIsDestroyable() should be(true)
      }(pending)*/
    }
    "of Type Stone" should {
      val stoneCell = Cell("Stone")
      "have walkthrough false" in {
        stoneCell.cellPassage() should be(false)
      }
      /*"have destroyable false" in {
        stoneCell.getIsDestroyable() should be(false)
      } (pending)*/
    }
    "of Type Grass" should {
      val grassCell = Cell("Grass")
      "have walkthrough true" in {
        grassCell.cellPassage() should be(true)
      }
      /*"have destroyable false" in {
        grassCell.getIsDestroyable() should be(true)
      }(pending)*/
    }
    "of Type else" should {
      val lukasCell = Cell("Lukas")
      "have walkthrough false" in {
        lukasCell.cellPassage() should be(false)
      }
    }
  }
  val grass = Cell("Grass")
  grass shouldBe a[Cell]
  grass should not be a[Obstacle]

  the[ArithmeticException] thrownBy 1 / 0 should have message "/ by zero"
  val s = ""
  var thrown = the[IndexOutOfBoundsException] thrownBy (s.charAt(-1))
  thrown.getMessage should equal("String index out of range: -1")
  val nill = null
  the[NullPointerException] thrownBy nill.toString().charAt(1)
  "text" should contain noneOf('f', 'A', 'a')
}
