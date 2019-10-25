package de.felite

case class Cell(groundItem:String) {
  private val ground = Obstacle(groundItem)
  def cellPassage(): Boolean = {
    ground.walkthrough
  }
}