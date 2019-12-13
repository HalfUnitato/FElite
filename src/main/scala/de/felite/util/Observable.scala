package de.felite.util

trait Observer {
  def update(observerCommand: ObserverCommand.Value): Unit
}

class Observable {
  var subscribers: Vector[Observer] = Vector()

  def add(s: Observer): Unit = subscribers = subscribers :+ s

  def remove(s: Observer): Unit = subscribers = subscribers.filterNot(o => o == s)

  def notifyObservers(observerCommand: ObserverCommand.Value): Unit = subscribers.foreach(_.update(observerCommand: ObserverCommand.Value))

}
