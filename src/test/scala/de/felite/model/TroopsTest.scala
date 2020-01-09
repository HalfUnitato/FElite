package de.felite.model

import de.felite.TestBaseClass
import de.felite.model.entity.figure.FootPatrol


class TroopsTest extends TestBaseClass {
  val player: Player = Player(_number = 1)
  "a new Troop" when {
    "a new Archer" should {
      //Archer(attV,defV,attR,mvR,xpos,ypos,owner)
      val archer = SoldierFactory.create('a',(0,1),20,player)
      val archer2 = SoldierFactory.create('a',(0,1),20,player)
      "be of Instance Archer" in {
        archer.isInstanceOf[FootPatrol] should be(true)
      }
      "have AttackRange" in {
        archer.attackRange should be > 0
        archer.attackRange should equal(4)
      }
      "have MoveRange" in {
        archer.moveRange should be > 0
        archer.moveRange should equal(2)
      }
      "have ATT" in {
        archer.attack should be > 0
        archer.attack should equal(1)
      }
      "have DEF" in {
        archer.defense should be > 0
        archer.defense should equal(2)
      }
      "should not be the same as another one" in {
        archer eq archer2 should be(false)
      }
    }
    "a new Soldier" should {
      //Archer(attV,defV,attR,mvR, health, Xpos, Ypos, Owner)
      val soldier = SoldierFactory.create('s',(0,0),20,player)
      val soldier2 = SoldierFactory.create('s',(0,1),20,player)
      "be of Instance Soldier" in {
        soldier.isInstanceOf[FootPatrol] should be(true)
      }
      "have AttackRange" in {
        soldier.attackRange should be > 0
        soldier.attackRange should equal(1)
      }
      "have MoveRange" in {
        soldier.moveRange should be > 0
        soldier.moveRange should equal(4)
      }
      "have ATT" in {
        soldier.attack should be > 0
        soldier.attack should equal(2)
      }
      "have DEF" in {
        soldier.defense should be > 0
        soldier.defense should equal(4)
      }
      "should not be the same" in {
        soldier.equals(soldier2) should be(false)
      }
    }
  }
  //game logictests go here
  /*"a skirmish " when {
    var archer = Archer(5, 3, 2, 4, 40, 0,0, player)
    var soldier = Soldier(4, 4, 1, 5, 50)
    "a soldier attacks a archer, the archer" should {
      "loose health" in {
        archer = Archer(5, 3, 2, 4, 36)
        archer.health === 36
      }
    }
    "a archer attacks a soldier, the soldier" should {
      "loose health" in {
        soldier = Soldier(4, 4, 1, 5, 45)
        soldier.health === 45
      }
    }
  }*/
}
