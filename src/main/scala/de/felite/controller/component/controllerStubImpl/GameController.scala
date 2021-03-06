package de.felite.controller.component.controllerStubImpl

import de.felite.controller.GameControllerInterface
import de.felite.controller.state.game.{P1State, State}
import de.felite.model._

class GameController extends GameControllerInterface {
  override var state: State = _
  override var player1: Player = _
  override var player2: Player = _
  override var currentPlayer: Player = _
  override var printString: String = _
  override var readString: String = _
  override var cmdStr: String = _
  override var btnStartCoord: (Int, Int) = _
  override var btnEndCoord: (Int, Int) = _

  override def init(): Unit = {
    println("------ Start of Initialisation ------")
    state = new State
    btnStartCoord = (-1, -1)
    btnEndCoord = (-1, -1)

    this.player1 = Player("Ike", Console.BLUE, 1)
    this.player2 = Player("Zelgius", Console.RED, 2)

    currentPlayer = player1

    state.gameState = P1State(this)
    state.gameState.handle()

  }

  override def fieldToString: String = ""

  override def doMove(): Boolean = true

  override def undo(): Unit = {}

  override def redo(): Unit = {}

  override def getPlayerName: String = currentPlayer.getPlayerName

  override def nextTurn(): Unit = {}

  override var field: Field = _

  override def load(fileName: String = "field.xml", size: Int): Unit = {}

  override def store(): Unit = {}
}
