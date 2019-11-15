package de.felite

class FEliteTest extends TestBaseClass {
"A Game" when {
  val game = FElite
  "initialized" should {
    "have a TUI" in {
      game.tui should not be null
    }
    "have a controller" in {
      game.controller should not be null
    }
  }
}
}
