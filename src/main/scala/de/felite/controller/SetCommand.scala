package de.felite.controller

import de.felite.model.Field
import de.felite.model.entity.Entity
import de.felite.model.entity.obstacle.Grass
import de.felite.util.Command

class SetCommand(xFrom: Int, yFrom: Int, entityFrom: Entity, xTo: Int, yTo: Int, entityTo: Entity) extends Command {
  override def doStep: Unit = {
    Field.setCell(Grass, xFrom, yFrom)
    Field.setCell(entityFrom, xTo, yTo)
  }

  override def undoStep: Unit = {
    Field.setCell(entityFrom, xFrom, yFrom)
    Field.setCell(entityTo, xTo, yTo)
  }

  override def redoStep: Unit = doStep
}