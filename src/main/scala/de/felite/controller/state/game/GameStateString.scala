package de.felite.controller.state.game

object GameStateString extends Enumeration {
  type GameState = Value
  val QUIT, INIT, END, P1, P2, P1_INI, P2_INI, PRINT_FIELD, NEXT_CMD, WON = Value

  val map: Map[GameState, String] = Map[GameState, String](
    P1_INI -> "Name of player number one:",
    P2_INI -> "Name of player number two:",
    P1 -> "'s Turn",
    P2 -> "'s Turn",
    NEXT_CMD -> "type in your command: ('help' if no idea)",
    QUIT -> "Quit Game",
    INIT -> "Initialisation",
    PRINT_FIELD -> "Field",
    END -> "End of Turn",
    WON -> "won!"
  )

  def message(gameState: GameState): String = {
    map(gameState)
  }
}
