package de.felite.controller.component.controllerImpl.controllerMockImpl

import de.felite.controller.GameControllerInterface
import de.felite.controller.state.game.State
import de.felite.model.Player
import de.felite.util.UndoManager

class GameController extends GameControllerInterface{
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
    state = new State()
    player1 = Player()
    player2 = Player()
    currentPlayer = player1
    printString = ""
    readString =""
    cmdStr = ""
    btnStartCoord = (1,2)
    btnEndCoord = (2,3)
  }

  override def FieldToString: String = "Field"

  override def doMove(): Boolean = true

  override def undo(): Unit = {}

  override def redo(): Unit = {}

  override def getPlayerName: String = currentPlayer.getPlayerName

  override def nextTurn(): Unit = {}
}
