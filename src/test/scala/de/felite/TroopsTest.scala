package de.felite


class TroopsTest extends TestBaseClass {
  "a new Troop" when {
    "a new Archer" should {
      //Archer(attV,defV,attR,mvR)
      val archer = Archer(5,3,2,4)
      val archer2 = Archer(2,4,2,4)
      "be of Instance Archer" in {
        archer.isInstanceOf[Archer] should be (true)
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
      "should not be the same" in {
        archer should not be archer2
      }
    }
    "a new Soldier" should {

    }
  }
}
