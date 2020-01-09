package de.felite.controller.component.controllerBaseImpl

import de.felite.controller.GameControllerInterface
import de.felite.model.{Entity, Troop}
import de.felite.util.Command

class SetCommand(controller:GameControllerInterface, xFrom: Int, yFrom: Int, entityFrom: Entity, xTo: Int, yTo: Int, entityTo: Entity) extends Command {
  override def doStep(): Unit = {
    controller.field.setCell(entityFrom, xFrom, yFrom)
    controller.field.setCell(entityTo, xTo, yTo)
  }

  override def undoStep(): Unit = {
    if(xFrom == xTo && yFrom == yTo && controller.field.getCell(xTo,yTo).sign == 'g')
      entityFrom.asInstanceOf[Troop].owner().addPlayerTroop(entityFrom.asInstanceOf[Troop])
    else {
      entityTo.asInstanceOf[Troop].owner().addPlayerTroop(entityTo.asInstanceOf[Troop])
    }
    controller.field.setCell(entityTo, xFrom, yFrom)
    controller.field.setCell(entityFrom, xTo, yTo)
  }

  override def redoStep(): Unit = doStep()
}
