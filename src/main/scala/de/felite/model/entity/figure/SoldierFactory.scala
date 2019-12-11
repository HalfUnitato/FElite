package de.felite.model.entity.figure

import de.felite.model.Player

object SoldierFactory {
  def create(typ:Char,pos:(Int,Int),healt:Int,player:Player):Troop={
    if(typ.equals('a'))
      BuildArcher.buildArcher(pos._1,pos._1,player,healt)
    else
      BuildSolider.buldSoldier(pos._1,pos._1,player,healt)
  }
}
