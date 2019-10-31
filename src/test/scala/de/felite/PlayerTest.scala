package de.felite

class PlayerTest extends TestBaseClass {
  "A Player" when {
    "created" should {
      val player = Player("Mercedes Maserati")
      "have a name" in {
        player.getPlayerName shouldEqual "Mercedes Maserati"
      }
      "have a unitAmount" in {
        player.getUnitAmount == 42
      }
    }
  }
}
