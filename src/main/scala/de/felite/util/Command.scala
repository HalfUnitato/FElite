package de.felite.util

trait Command {
  def doStep:Unit
  def undoStep:Unit
  def redoStep:Unit
}