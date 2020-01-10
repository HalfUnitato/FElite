package de.felite.model

import de.felite.TestBaseClass

class PlayerTest extends TestBaseClass {
  "A Player" when {
    val player = Player("Mercedes Maserati", Console.BLUE,1)
    val archer1: Troop = SoldierFactory.create('a',1, player)
    val archer2: Troop = SoldierFactory.create('a',1, player)
    val soldier1: Troop = SoldierFactory.create('a',1, player)
    val soldier2: Troop = SoldierFactory.create('a',1, player)
    val soldierX: Troop = SoldierFactory.create('a',1, player)

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
