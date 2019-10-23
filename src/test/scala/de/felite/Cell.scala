package de.felite

case class Cell(groundItem:String) {
  val ground = Obstacle(groundItem)
  def cellPassage(): Boolean = {
    ground.walkthrough
  }
}
