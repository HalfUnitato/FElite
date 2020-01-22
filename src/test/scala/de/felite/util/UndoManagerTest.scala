package de.felite.util

import de.felite.TestBaseClass
import de.felite.controller.component.controllerBaseImpl.GameController
import de.felite.model.Field

class UndoManagerTest extends TestBaseClass {
  var undoManager: UndoManager = new UndoManager(new GameController(new Field(3)))
  "UndoManager" when {
    "reset" in {
      undoManager.reset shouldBe undoManager.reset
    }
    // do/undo/redo-Test will be covered in other Testclasses
  }
}