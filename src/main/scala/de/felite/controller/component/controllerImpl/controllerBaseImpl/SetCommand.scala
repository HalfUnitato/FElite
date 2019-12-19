package de.felite.controller.component.controllerImpl.controllerBaseImpl

import de.felite.model.Field
import de.felite.model.entity.Entity
import de.felite.model.entity.figure.Troop
import de.felite.model.entity.obstacle.Grass
import de.felite.util.Command

class SetCommand(xFrom: Int, yFrom: Int, entityFrom: Entity, xTo: Int, yTo: Int, entityTo: Entity) extends Command {
  override def doStep(): Unit = {
    Field.setCell(entityFrom, xFrom, yFrom)
    Field.setCell(entityTo, xTo, yTo)
  }

  override def undoStep(): Unit = {
    if(xFrom == xTo && yFrom == yTo && Field.getCell(xTo,yTo).sign() == Grass.sign)
      entityFrom.asInstanceOf[Troop].owner().addPlayerTroop(entityFrom.asInstanceOf[Troop])
    else {
      entityTo.asInstanceOf[Troop].owner().addPlayerTroop(entityTo.asInstanceOf[Troop])
    }
    Field.setCell(entityTo, xFrom, yFrom)
    Field.setCell(entityFrom, xTo, yTo)
  }

  override def redoStep(): Unit = doStep()
}
