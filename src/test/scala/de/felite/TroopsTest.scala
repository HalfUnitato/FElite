package de.felite

class TroopsTest extends TestBaseClass {
  "a Unit" when {
    "a new Archer" should {
      val archer = Troops("Archer",2,4)
        "have Value Archer" in {
          archer.getType should be ("Archer") //Strings doof? lieber Zahlen?
        }
        "have AttackRange" in {
          archer.getAttRange should be > 0
          archer.getAttRange should equal(2)
        }
        "have MoveRange" in {
          archer.getMvRange should be > 0
          archer.getMvRange should equal(4)
        }
    }
    "an Soldier" should {
      val soldier = Troops("Soldier",1,5)
        "have KindOf Soldier" in {
          soldier.getType should be ("Soldier")
        }
    }
  }
}
