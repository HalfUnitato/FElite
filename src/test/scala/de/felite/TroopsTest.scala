package de.felite


class TroopsTest extends TestBaseClass {
  "a new Troop" when {
    "a new Archer" should {
      //Archer(attV,defV,attR,mvR)
      val archer = Archer(5, 3, 2, 4, 40)
      val archer2 = Archer(5, 3, 2, 4, 40)
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
      //Archer(attV,defV,attR,mvR)
      val soldier = Soldier(4, 4, 1, 5, 50)
      val soldier2 = Soldier(4, 4, 1, 5, 50)
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
        soldier eq soldier2 should be(false)
      }
    }
  }
  "a skirmish " when {
    var archer = Archer(5, 3, 2, 4, 40)
    var soldier = Soldier(4, 4, 1, 5, 50)
    "a soldier attacks a archer, the archer" should {
      "loose health" in {
        archer = Archer(5,3,2,4,36)
        archer.health === 36
      }
    }
    "a archer attacks a soldier, the soldier" should {
      "loose health" in {
        soldier = Soldier(4,4,1,5,45)
        soldier.health === 45
      }
    }
  }
}
