package de.felite

case class Obstacle(groundItem:String) {
  var walkthrough = false //two vals where one is true the other false?
  groundItem match {
    case "Stone" => walkthrough = false
    case "Tree" => walkthrough = false
    case "Grass" => walkthrough = true
    case _ =>
  }
}
