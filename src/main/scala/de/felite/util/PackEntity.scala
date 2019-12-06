package de.felite.util

import de.felite.model.entity.figure.Troop

class PackTroops[T](val entity:List[T]) {
  def map(t:T=>T) = entity.map(entity => t(entity))
}