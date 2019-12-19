package de.felite.model

import de.felite.TestBaseClass
import de.felite.model.entity.figure.{Archer, Soldier}

class PlayerTest extends TestBaseClass {
  "A Player" when {
    val player = Player("Mercedes Maserati", Console.BLUE)
    val archer1: Troop = Archer(1, 1, 1, 1, 1, 0, 1, player)
    val archer2: Troop = Archer(1, 1, 1, 1, 1, 0, 2, player)
    val soldier1: Troop = Soldier(1, 1, 1, 1, 1, 0, 3, player)
    val soldier2: Troop = Soldier(1, 1, 1, 1, 1, 0, 4, player)
    val soldierX: Troop = Soldier(9, 1, 1, 1, 1, 0, 4, player)

    "created" should {
      "have a name" in {
        player.getPlayerName == "Mercedes Maserati"
      }
      "name is not empty" in {
        !player.getPlayerName.isEmpty
      }
      "have a unitAmount (which is useless)" in {
        player.getUnitAmount == 42
      }
      "can add troops" in {
        player.addPlayerTroop(archer1) shouldBe true
        player.addPlayerTroop(archer2) shouldBe true
        player.addPlayerTroop(soldier1) shouldBe true
        player.addPlayerTroop(soldier2) shouldBe true
        player.addPlayerTroop(soldier2) shouldBe false
        // field.setSoldier(soldier, x, y)
      }
      "can check for troops" in {
        player.containsSoldier(archer1.asInstanceOf[Troop]) shouldBe true
        player.containsSoldier(archer2.asInstanceOf[Troop]) shouldBe true
        player.containsSoldier(soldier1.asInstanceOf[Troop]) shouldBe true
        player.containsSoldier(soldier2.asInstanceOf[Troop]) shouldBe true
        player.containsSoldier(soldierX.asInstanceOf[Troop]) shouldBe false
      }
      "can remove troops" in {
        player.removeTroop(archer1) shouldBe true
        player.removeTroop(archer2) shouldBe true
        player.removeTroop(soldier1) shouldBe true
        player.removeTroop(soldier2) shouldBe true
        player.removeTroop(soldier2) shouldBe false
      }
    }
  }
}
