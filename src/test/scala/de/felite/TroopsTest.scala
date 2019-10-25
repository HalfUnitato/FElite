package de.felite


class TroopsTest extends TestBaseClass {
  "a Unit" when {
    "a new Archer" should {
      val archer = Archer(2,4)
      val archer2 = Archer(3,4)
        "be of Instance Archer" in {
          archer.isInstanceOf[Archer] should be (true)
        }
        "have AttackRange 2" in {
          archer.attackRange should be > 0
          archer.attackRange should equal(2)
        }
        "have MoveRange 5" in {
          archer.moveRange should be > 0
          archer.moveRange should equal(4)
        }
        "should not be the same" in {
          archer should not be archer2
        }
    }
    "a new Soldier" should {

    }
  }
}
