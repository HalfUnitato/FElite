package de.felite.util

private class PackTroops[T](val entity:List[T]) {
  def map(t: T => T): Seq[Any] = entity.map(entity => t(entity))
}