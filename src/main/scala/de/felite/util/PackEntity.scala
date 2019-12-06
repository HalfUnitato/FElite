package de.felite.util

import de.felite.model.entity.figure.Troop

class PackTroops[T](val entity:List[T]) {
  def map(f:T=>T) = entity.map(entity => f(entity))
}
class CrateT[T] (val packs:List[T]){
  def map(f:T => T) = packs.map(pack => f(pack))
  def flatMap(f:T=> List[Troop]) = packs.flatMap((pack => f(pack)))
}

/*
case class Some[Troop] (val t:Troop) extends Option[Troop] {}
def map(f:Troop => Troop) = new Some(f(t))
*/
