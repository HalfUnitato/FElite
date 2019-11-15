package de.felite.model.entity.obstacle

import de.felite.model.entity.Entity

trait Obstacle extends Entity {


  def walkthrough(): Boolean = false

  def color(): String = "WHITE"

  def getColor:String = color()
}
