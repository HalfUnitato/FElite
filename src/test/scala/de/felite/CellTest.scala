package de.felite

class CellTest extends TestBaseClass {
  "A Obstacle" when {
    "as Tree" should {
      "has walkthrough tree = false" in {
        val tree = Tree
        tree.walkThrough should be
        false
      }
    }
    "as Grass" should {
      "has walkThrough false" in {
        val grass = Grass
        grass.walkThrough should be
        true
      }
      "as Rock" should {
        "has walkThrough false" in {
          val rock = Rock
          rock.walkThrough should be
          false
        }
      }
    }
  }
}