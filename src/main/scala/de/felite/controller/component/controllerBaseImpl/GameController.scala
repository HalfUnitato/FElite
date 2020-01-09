package de.felite.controller.component.controllerBaseImpl

import com.google.inject.{Guice, Inject, Injector}
import de.felite.FEliteModule
import de.felite.controller.GameControllerInterface
import de.felite.controller.state.game.GameStateString._
import de.felite.controller.state.game.{P1State, PrintFieldState, State, WonState}
import de.felite.model._
import de.felite.util.UndoManager
import de.felite.util.fileIOComponent.FileIOInterface

import scala.util.{Failure, Success, Try}

class GameController @Inject()(_field: Field) extends GameControllerInterface {
  override var field: Field = _field
  override var state: State = _
  private var undoManager: UndoManager = _
  override var player1: Player = _
  override var player2: Player = _
  override var currentPlayer: Player = _
  override var printString: String = _
  override var readString: String = _
  override var cmdStr: String = _
  override var btnStartCoord: (Int, Int) = _
  override var btnEndCoord: (Int, Int) = _
  val injector: Injector = Guice.createInjector(new FEliteModule)
  val fileIO: FileIOInterface = injector.getInstance(classOf[FileIOInterface])

  override def init(): Unit = {
    /*field.getScale match {
      case 1 => field = injector.instance[Field](Names.named("test"))
      case 4 => field = injector.instance[Field](Names.named("middle"))
      case 9 => field = injector.instance[Field](Names.named("max"))
      case _ =>
    }*/

    println("------ Start of Initialisation ------")
    state = new State
    undoManager = new UndoManager(this)
    btnStartCoord = (-1, -1)
    btnEndCoord = (-1, -1)
    //    println("------ Start of Initialisation ------")

    this.player1 = Player("Ike", Console.BLUE, 1)
    this.player2 = Player("Zelgius", Console.RED, 2)

    setUserTroopsDefault("TopLeft", player1)
    setUserTroopsDefault("BottomRight", player2)

    currentPlayer = player1


    //    println("------ End of Initialisation ------")
    state.gameState = P1State(this)
    state.gameState.handle()


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
      y = field.getScale - 1
      x = field.getScale - 2
    }

    val soldier = SoldierFactory.create(typ = 's', pos = (x, y), player = player)
    val archer = SoldierFactory.create(typ = 'a', pos = (x, y), player = player)

    player.addPlayerTroop(soldier.asInstanceOf[Troop])
    field.setCell(soldier, x, y)

    x += 1

    player.addPlayerTroop(archer.asInstanceOf[Troop])
    field.setCell(archer, x, y)
  }

  override def fieldToString: String = field.toString

  override def doMove(): Boolean = {
    val from: (Int, Int) = btnStartCoord
    val to: (Int, Int) = btnEndCoord
    Try(field.getCell(from._1, from._2), field.getCell(to._1, to._2)) match {
      case Success(_) =>
        val bool = movement((from._1, from._2), (to._1, to._2))
        state.gameState = PrintFieldState(this)
        state.gameState.handle()
        bool
      case Failure(_: Throwable) =>
        false
    }
  }

  private def movement(from: (Int, Int), to: (Int, Int)): Boolean = {
    // is usage of troop valid?
    val fEntity: Entity = field.getCell(from._1, from._2)

    if (!currentPlayer.containsSoldier(fEntity))
      return false

    // is destination valid?
    val tEntity: Entity = field.getCell(to._1, to._2)
    var range: Int = 0

    // destination == source or friendly mate
    if (from == to || currentPlayer.containsSoldier(tEntity))
      return false

    // Troop from enemy?
    if (tEntity.isInstanceOf[Troop] && !currentPlayer.containsSoldier(tEntity))
      range = fEntity.asInstanceOf[Troop].attackRange()
    // or Grass -> no Rock / Tree
    else if (tEntity.walkThrough) {
      range = fEntity.asInstanceOf[Troop].moveRange()
    } else return false

    alreadyVisited = Nil

    if (!movePlausiR(from, to, range))
      return false

    // attack
    if (field.getCell(to._1, to._2).isInstanceOf[Troop]) {
      doAttack(from, fEntity, to, tEntity)
    }
    // move
    else {
      undoManager.doStep(new SetCommand(this, from._1, from._2, ObstacleFactory.create('g', from._1, from._2),
        to._1, to._2, fEntity))
    }
    true
  }

  private def doAttack(from: (Int, Int), fEntity: Entity, to: (Int, Int), tEntity: Entity): Unit = {
    undoManager.doStep(new SetCommand(this, to._1, to._2, tEntity,
      to._1, to._2,
      if (tEntity.asInstanceOf[Troop].health() - fEntity.asInstanceOf[Troop].attack() <= 0) {
        ObstacleFactory.create('g', to._1, to._2)
      } else {
        val tmp = SoldierFactory.create(
          tEntity.sign, to,
          tEntity.asInstanceOf[Troop].health() - fEntity.asInstanceOf[Troop].attack(),
          tEntity.asInstanceOf[Troop].owner()
        )
        tmp.owner().addPlayerTroop(tmp)
        tmp
      }))
    tEntity.asInstanceOf[Troop].owner().removeTroop(tEntity.asInstanceOf[Troop])
    isEnd
  }

  private var alreadyVisited: List[(Int, Int)] =
    Nil

  private def movePlausiR(cP: (Int, Int), goal: (Int, Int), range: Int): Boolean = {
    Try(field.getCell(cP._1, cP._2)) match {
      case Failure(e) => return false
      case Success(s) =>
    }
    if (cP._1 == goal._1 && cP._2 == goal._2)
      return true
    if (range == 0)
      return false
    if (alreadyVisited.contains(cP))
      return false
    val tmp = field.getCell(cP._1, cP._2)
    if (!tmp.walkThrough)
      return false
    alreadyVisited = cP :: alreadyVisited
    for {x <- cP._1 - 1 to cP._1 + 1
         y <- cP._2 - 1 to cP._2 + 1} {
      if (movePlausiR((x, y), goal, range - 1))
        return true
    }
    false
  }

  override def undo(): Unit = {
    undoManager.undoStep()
    state.gameState = PrintFieldState(this)
    state.gameState.handle()
  }

  override def redo(): Unit = {
    undoManager.redoStep()
    state.gameState = PrintFieldState(this)
    state.gameState.handle()
  }

  def load(): Unit = {
    player1.clearToopList()
    field = fileIO.load(this)
  }

  def store(): Unit = {
    fileIO.store(field)
  }

  private def isEnd: Any = {
    if (player1.getUnitAmount == 0 || player2.getUnitAmount == 0 || state.gameState.state == QUIT) {
      state.gameState = WonState(this)
      state.gameState.handle()
    }
  }

  override def getPlayerName: String =
    currentPlayer.getPlayerName

  override def nextTurn(): Unit = undoManager.reset()
}