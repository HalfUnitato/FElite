package de.felite.model

import de.felite.TestBaseClass
import de.felite.model.entity.figure.{Archer, BuildArcher, Soldier}


class TroopsTest extends TestBaseClass {
  val player: Player = Player()
  "a new Troop" when {
    "a new Archer" should {
      //Archer(attV,defV,attR,mvR,xpos,ypos,owner)
      val archer = Archer(5, 3, 2, 4, 40, 0, 0, player)
      val archer2 = BuildArcher.buildArcher(0, 0, player)
      "be of Instance Archer" in {
        archer.isInstanceOf[Archer] should be(true)
      }
      "have AttackRange" in {
        archer.attackRange should be > 0
        archer.attackRange should equal(2)
      }
      "have MoveRange" in {
        archer.moveRange should be > 0
        archer.moveRange should equal(4)
      }
      "have ATT" in {
        archer.attack should be > 0
        archer.attack should equal(5)
      }
      "have DEF" in {
        archer.defense should be > 0
        archer.defense should equal(3)
      }
      "should not be the same as another one" in {
        archer eq archer2 should be(false)
      }
    }
    "a new Soldier" should {
      //Archer(attV,defV,attR,mvR, health, Xpos, Ypos, Owner)
      val soldier = Soldier(4, 4, 1, 5, 50, 0, 0, player)
      val soldier2 = BuildArcher.buildArcher(0, 0, player)
      "be of Instance Soldier" in {
        soldier.isInstanceOf[Soldier] should be(true)
      }
      "have AttackRange" in {
        soldier.attackRange should be > 0
        soldier.attackRange should equal(1)
      }
      "have MoveRange" in {
        soldier.moveRange should be > 0
        soldier.moveRange should equal(5)
      }
      "have ATT" in {
        soldier.attack should be > 0
        soldier.attack should equal(4)
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
