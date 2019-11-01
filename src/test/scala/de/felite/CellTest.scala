package de.felite

import de.felite.obstacle.{Grass, Rock, Tree}

class CellTest extends TestBaseClass {
  "A Obstacle" when {
    "as Tree" should {
      val tree = Tree
      "has walkthrough " in {
        tree.walkThrough should be
        false
      }
      "has sign " in {
        tree.sign === 't'
      }
    }
    "as Grass" should {
      val grass = Grass
      "has walkThrough " in {
        grass.walkThrough should be
        true
      }
      "has sign " in {
        grass.sign === 'g'
      }
      "as Rock" should {
        val rock = Rock
        "has walkThrough " in {
          rock.walkThrough should be
          false
        }
        "has sign " in {
          rock.sign === 'r'
        }
      }
    }
  }
}