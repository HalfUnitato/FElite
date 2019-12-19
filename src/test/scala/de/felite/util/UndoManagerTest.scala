package de.felite.util

import de.felite.TestBaseClass
import de.felite.controller.component.controllerImpl.controllerBaseImpl.GameController
import de.felite.model.entity.Entity

class UndoManagerTest extends TestBaseClass {
  var undoManager: UndoManager = new UndoManager(new GameController)
  "UndoManager" when {
    "reset" in {
      undoManager.reset shouldBe undoManager.reset
    }
    // do/undo/redo-Test will be covered in other Testclasses
  }
}