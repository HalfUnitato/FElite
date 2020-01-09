package de.felite.util.fileIOComponent

import de.felite.controller.GameControllerInterface
import de.felite.model.Field

trait FileIOInterface {
  def load(controller: GameControllerInterface): Field

  def store(field: Field): Unit
}
