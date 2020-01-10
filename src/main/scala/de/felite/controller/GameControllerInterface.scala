package de.felite.controller

import de.felite.controller.state.game.State
import de.felite.model.{Field, Player}
import de.felite.util.Observable

trait GameControllerInterface extends Observable {
  var field: Field
  var state: State
  var player1: Player
  var player2: Player
  var currentPlayer: Player
  var printString: String
  var readString: String
  var cmdStr: String
  var btnStartCoord: (Int, Int)
  var btnEndCoord: (Int, Int)

  def init(): Unit

  def fieldToString: String

  def doMove(): Boolean

  def undo(): Unit

  def redo(): Unit

  def load(fileName: String = "field.xml", size: Int = -1): Unit

  def store(): Unit

  def getPlayerName: String

  def nextTurn(): Unit
}
