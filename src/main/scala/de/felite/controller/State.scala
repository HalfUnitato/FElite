package de.felite.controller

import de.felite.controller.StateContext.Player1Switch

object StateContext {
  var state: State = _

  trait State {
    def handle(): State
  }

  case class Player1Switch() extends State {
    override def handle(): State = {
      state = Player2Switch()
      state
    }
  }

  case class Player2Switch() extends State {
    override def handle(): State = {
      state = Player1Switch()
      state
    }
  }
}