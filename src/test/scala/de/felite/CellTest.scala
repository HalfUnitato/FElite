package de.felite

class CellTest extends TestBaseClass {
  "A Cell" when {
    "of Type Tree" should {
      val treeCell = Cell("Tree")
      "have walkthrough false" in {
        treeCell.cellPassage() should be(false)
      }
    }
    "of Type Stone" should {
      val stoneCell = Cell("Stone")
      "have walkthrough false" in {
        stoneCell.cellPassage() should be(false)
      }
    }
    "of Type Grass" should {
      val grassCell = Cell("Grass")
      "have walkthrough true" in {
        grassCell.cellPassage() should be(true)
      }
    }
  }
}
