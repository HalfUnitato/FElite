package de.felite.util

import de.felite.controller.GameController

class UndoManager(contoller:GameController) {
  private var undoStack: List[Command] = Nil
  private var redoStack: List[Command] = Nil

  def doStep(command: Command) = {
    undoStack = command :: undoStack
    command.doStep
  }

  def undoStep = {
    undoStack match {
      case Nil =>
      case head :: stack => {
        head.undoStep
        undoStack = stack
        redoStack = head :: redoStack
      }
    }
  }

  def redoStep = {
    redoStack match {
      case Nil =>
      case head :: stack => {
        head.redoStep
        redoStack = stack
        undoStack = head :: undoStack
      }
    }
  }
  def reset={
    undoStack = Nil
    redoStack = Nil
  }
}
