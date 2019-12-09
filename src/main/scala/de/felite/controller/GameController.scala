package de.felite.controller

import de.felite.controller.status._
import de.felite.controller.status.GameStateString._
import de.felite.model.entity.Entity
import de.felite.model.{Field, Player}
import de.felite.model.entity.figure.{Archer, BuildArcher, BuildSolider, Soldier, Troop}
import de.felite.util.{Observable, ObserverCommand, UndoManager}
import de.felite.util.ObserverCommand._

class GameController() extends Observable {
  val undoManager = new UndoManager(this)
  var player1: Player = _
  var player2: Player = _
  var currentPlayer: Player = player1
  var printString: String = _
  var readString: String = _
  var cmdStr: String = _


  def init(testflag: Int = 0): Unit = {
    println("------ Start of Initialisation ------")

    if (testflag == 0) {
      State.gameState = new P1InitState(this)
      State.gameState.handle
      this.player1 = Player(readString, Console.BLUE)

      State.gameState = new P2InitState(this)
      State.gameState.handle
      this.player2 = Player(readString, Console.RED)
    } else {
      this.player1 = Player("Peter Hans")
      this.player2 = Player("Hans Peter")
    }

    setUserTroopsDefault("TopLeft", player1)
    setUserTroopsDefault("BottomRight", player2)

    currentPlayer = player1

    State.gameState = new P1State(this)
    State.gameState.handle

    println("------ End of Initialisation ------")

    //gameState = new PrintFieldState(this)
    //notifyObservers(PRINTSTRING)

    //gameState = new P1State(this)
  }

  private def setUserTroopsDefault(pos: String, player: Player): Unit = {
    var y = 0
    var x = 0

    if (pos.equals("TopLeft")) {
      y = 0
      x = 0
    }
    else if (pos.equals("BottomRight")) {
      y = Field.getScal - 1
      x = Field.getScal - 2
    }

    val soldier = BuildSolider.buldSoldier(x, y, player)
    val archer = BuildArcher.buildArcher(x, y, player)

    player.addPlayerTroop(soldier.asInstanceOf[Troop])
    Field.setCell(soldier, x, y)

    x += 1

    player.addPlayerTroop(archer.asInstanceOf[Troop])
    Field.setCell(archer, x, y)
  }

  def FieldToString = Field.toString

  def movement(from: (Int, Int), to: (Int, Int)): Boolean = {
    val fEntity: Entity = Field.getCell(from._1, from._2)
    if (!currentPlayer.containsSoldier(fEntity))
      return false

    val tEntity: Entity = Field.getCell(to._1, to._2)
    val tmpScal = Field.getScal

    //check if currentPlayer owns Soldier specified at from
    if (currentPlayer.containsSoldier(Field.getCell(from._2, from._1))) {
      undoManager.doStep(new SetCommand(from._1, from._2, Field.getCell(from._1, from._2), to._1, to._2, Field.getCell(to._1, to._2)))
      //gameState = new PrintFieldState(this)
      //notifyObservers(ObserverCommand.PRINTSTRING)
      return true
    }
    false
  }

  private def attack(from: (Int, Int), to: (Int, Int)): Boolean = {
    // missing plausi-Check -----
    //undoManager.doStep(new SetCommand(to._1, to._2, Field.getCell(from._1, from._2),
    //                                  to._1, to._2, Field.getCell(to._1, to._2)))
    State.gameState = new PrintFieldState(this)
    true
  }

  def undo = {
    undoManager.undoStep
    State.gameState = new PrintFieldState(this)
  }

  def redo = {
    undoManager.redoStep
    State.gameState = new PrintFieldState(this)
  }

  def isEnd: Boolean =
    player1.getUnitAmount == 0 || player2.getUnitAmount == 0 || State.gameState.state == QUIT

  def getPlayerName: String =
    currentPlayer.getPlayerName

}
