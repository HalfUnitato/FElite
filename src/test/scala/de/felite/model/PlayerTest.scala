package de.felite.model

import de.felite.TestBaseClass
import de.felite.model.figure.{Archer, Soldier, Troop}
import de.felite.model.obstacle.Obstacle
import de.felite.util.ReturnValues

class PlayerTest extends TestBaseClass {
  "A Player" when {
    val player = Player("Mercedes Maserati")
    val archer1: Troop = Archer(1, 1, 1, 1, 1, 0, 1)
    val archer2: Troop = Archer(1, 1, 1, 1, 1, 0, 2)
    val soldier1: Troop = Soldier(1, 1, 1, 1, 1, 0, 3)
    val soldier2: Troop = Soldier(1, 1, 1, 1, 1, 0, 4)
    val soldierX: Troop = Soldier(9, 1, 1, 1, 1, 0, 4)

    "created" should {
      "have a name" in {
        player.getPlayerName == "Mercedes Maserati"
      }
      "name is not empty" in {
        !player.getPlayerName.isEmpty
      }
      "have a unitAmount" in {
        player.getUnitAmount == 42
      }
      "can add troops" in {
        player.addPlayerTroop(archer1) shouldBe ReturnValues.VALID
        player.addPlayerTroop(archer2) shouldBe ReturnValues.VALID
        player.addPlayerTroop(soldier1) shouldBe ReturnValues.VALID
        player.addPlayerTroop(soldier2) shouldBe ReturnValues.VALID
        player.addPlayerTroop(soldier2) shouldBe ReturnValues.INVALID
        // field.setSoldier(soldier, x, y)
      }
      "can check for troops" in {
        player.containsSoldier(archer1.asInstanceOf[Obstacle]) shouldBe ReturnValues.VALID
        player.containsSoldier(archer2.asInstanceOf[Obstacle]) shouldBe ReturnValues.VALID
        player.containsSoldier(soldier1.asInstanceOf[Obstacle]) shouldBe ReturnValues.VALID
        player.containsSoldier(soldier2.asInstanceOf[Obstacle]) shouldBe ReturnValues.VALID
        player.containsSoldier(soldierX.asInstanceOf[Obstacle]) shouldBe ReturnValues.INVALID
      }
      "can remove troops" in {
        player.removeTroop(archer1) shouldBe ReturnValues.VALID
        player.removeTroop(archer2) shouldBe ReturnValues.VALID
        player.removeTroop(soldier1) shouldBe ReturnValues.VALID
        player.removeTroop(soldier2) shouldBe ReturnValues.VALID
        player.removeTroop(soldier2) shouldBe ReturnValues.INVALID
      }
    }
  }
}
