package de.felite

import de.felite.io.Tui

class GameControl() {
  private var field: Field = _
  Tui.printField(field)

  def init(): Unit = {
    field = Field("C:\\Users\\Unitato\\Documents\\HTWG-Konstanz" +
      "\\3-Semester\\SoftwareEngineering" +
      "\\Tut\\FElite\\src\\fieldTest.txt")
  }

}