package de.felite.controller
import de.felite.model.Player
import de.felite.util.{Observable, UndoManager}

trait GameControllerInterface extends Observable  {

  var undoManager: UndoManager
  var player1: Player
  var player2: Player
  var currentPlayer: Player
  var printString: String
  var readString: String
  var cmdStr: String
  var btnStartCoord: (Int, Int)
  var btnEndCoord: (Int, Int)

  def init(): Unit

  def FieldToString: String

  def doMove(): Boolean

  def undo(): Unit

  def redo(): Unit

  def isEnd: Boolean

  def getPlayerName: String
}
