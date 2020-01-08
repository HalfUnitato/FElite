package de.felite.util.fileIOComponent

import de.felite.model.Field

trait FileIOInterface {
  def load: Field

  def store(field: Field): Unit
}
