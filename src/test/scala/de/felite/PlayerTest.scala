package de.felite

class PlayerTest extends TestBaseClass {
  "A Player" when {
    "created" should {
      val player = Player("Mercedes Maserati")
      "have a name" in {
        player.getPlayerName == "Mercedes Maserati"
      }
      "name is not empty" in{
        !player.getPlayerName.isEmpty
      }
      "have a unitAmount" in {
        player.getUnitAmount == 42
      }
    }
  }
}
