package de.felite.controller

object GameState extends Enumeration {
  type GameState = Value
  val QUIT, INIT, END, P1, P2 = Value

  val map = Map[GameState, String] (
    QUIT -> "Quit Game",
    INIT -> "Initialisation",
    END -> "End of Turn",
    P1 -> "'s Turn",
    P2 -> "'s Turn"
  )

  def message(gameState: GameState) = {
    map(gameState)
  }
}
