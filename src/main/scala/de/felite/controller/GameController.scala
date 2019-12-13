package de.felite.controller

import de.felite.controller.status._
import de.felite.controller.status.GameStateString._
import de.felite.model.entity.Entity
import de.felite.model.{Field, Player}
import de.felite.model.entity.figure.{Archer, BuildArcher, BuildSolider, Soldier, SoldierFactory, Troop}
import de.felite.model.entity.obstacle.{Grass, Obstacle}
import de.felite.util.{Observable, ObserverCommand, UndoManager}
import de.felite.util.ObserverCommand._
import de.felite.view.gui.InitGui

import scala.util.{Failure, Success, Try}

class GameController() extends Observable {
  val undoManager = new UndoManager(this)
  var player1: Player = _
  var player2: Player = _
  var currentPlayer: Player = player1
  var printString: String = _
  var readString: String = _
  var cmdStr: String = _
  var btnStartCoord:(Int, Int) = (-1,-1)
  var btnEndCoord:(Int, Int) = (-1,-1)


  def init(testflag: Int = 0): Unit = {
    println("------ Start of Initialisation ------")

    if (testflag == 0) {
//      val initgui = new InitGui(this)
      State.gameState = P1InitState(this)
      State.gameState.handle()
      this.player1 = Player(readString, Console.BLUE)

      State.gameState = P2InitState(this)
      State.gameState.handle()
      this.player2 = Player(readString, Console.RED)
    } else {
      this.player1 = Player("Peter Hans")
      this.player2 = Player("Hans Peter")
    }

    setUserTroopsDefault("TopLeft", player1)
    setUserTroopsDefault("BottomRight", player2)

    currentPlayer = player1

    State.gameState = P1State(this)
    State.gameState.handle()
//    player1.playerTroops.clear()
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
      y = Field.getScale - 1
      x = Field.getScale - 2
    }

    val soldier = BuildSolider.buldSoldier(x, y, player)
    val archer = BuildArcher.buildArcher(x, y, player)

    player.addPlayerTroop(soldier.asInstanceOf[Troop])
    Field.setCell(soldier, x, y)

    x += 1

    player.addPlayerTroop(archer.asInstanceOf[Troop])
    Field.setCell(archer, x, y)
  }

  def FieldToString: String = Field.toString

  def tryMove(from:(Int,Int),to:(Int,Int)):Boolean = {
    Try(Field.getCell(from._1,from._2), Field.getCell(to._1, to._2)) match {
      case Success(v) =>
        val bool = movement((from._1,from._2), (to._1, to._2))
        State.gameState = PrintFieldState(this)
        State.gameState.handle()
        bool
      case Failure(e) =>
        false
    }
  }
  def movement(from: (Int, Int), to: (Int, Int)): Boolean = {
    // is usage of troop valid?
    val fEntity: Entity = Field.getCell(from._1, from._2)

    if (!currentPlayer.containsSoldier(fEntity))
      return false

    // is destination valid?
    val tEntity: Entity = Field.getCell(to._1, to._2)
    var range: Int = 0

    // Troop from enemy?
    if (tEntity.isInstanceOf[Troop] && !currentPlayer.containsSoldier(tEntity)) {
      range = fEntity.asInstanceOf[Troop].attackRange()
    }
    // or Grass -> no Rock / Tree
    else if (tEntity.sign() == Grass.sign) {
      range = fEntity.asInstanceOf[Troop].moveRange()
    } else return false

    alreadyVisited = Nil

    if (!movePlausiR(from, to, range))
      return false

    // attack
    if (Field.getCell(to._1, to._2).isInstanceOf[Troop]) {
      doAttack(from,fEntity,to,tEntity)
    }
    // move
    else {
      undoManager.doStep(new SetCommand(from._1, from._2, Grass,
        to._1, to._2, fEntity))
    }
    true
  }
private def doAttack(from:(Int,Int),fEntity:Entity,to:(Int,Int),tEntity:Entity): Unit ={
    undoManager.doStep(new SetCommand(to._1, to._2, tEntity,
    to._1, to._2,
    if (tEntity.asInstanceOf[Troop].health() - fEntity.asInstanceOf[Troop].attack() <= 0) {
      Grass
    } else {
      val tmp = SoldierFactory.create(
        tEntity.sign(), to,
        tEntity.asInstanceOf[Troop].health() - fEntity.asInstanceOf[Troop].attack(),
        tEntity.asInstanceOf[Troop].owner()
      )
      tmp.owner().addPlayerTroop(tmp)
      tmp
    }))
  tEntity.asInstanceOf[Troop].owner().removeTroop(tEntity.asInstanceOf[Troop])
}
  private var alreadyVisited: List[(Int, Int)] = Nil

  def movePlausiR(cP: (Int, Int), goal: (Int, Int), range: Int): Boolean = {
    Try(Field.getCell(cP._1, cP._2)) match {
      case Failure(e) => return false
      case Success(s) =>
    }
    if (cP._1 == goal._1 && cP._2 == goal._2)
      return true
    if (range == 0)
      return false
    if (alreadyVisited.contains(cP))
      return false
    val tmp = Field.getCell(cP._1, cP._2)
    if (!tmp.isInstanceOf[Troop] && tmp.asInstanceOf[Obstacle].sign() != Grass.sign)
      return false
    alreadyVisited = cP :: alreadyVisited
    for {x <- cP._1 - 1 to cP._2 + 1
         y <- cP._1 - 1 to cP._2 + 1} {
      if (movePlausiR((x, y), goal, range - 1))
        return true
    }
    false
  }

  def undo(): Unit = {
    undoManager.undoStep
    State.gameState = PrintFieldState(this)
    State.gameState.handle()
  }

  def redo(): Unit = {
    undoManager.redoStep
    State.gameState = PrintFieldState(this)
    State.gameState.handle()
  }

  def isEnd: Boolean =
    player1.getUnitAmount == 0 || player2.getUnitAmount == 0 || State.gameState.state == QUIT

  def getPlayerName: String =
    currentPlayer.getPlayerName

}
