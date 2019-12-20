package de.felite

import com.google.inject.AbstractModule
import de.felite.controller.GameControllerInterface
import net.codingwell.scalaguice.ScalaModule

class FEliteModule extends AbstractModule with ScalaModule {

  val defaultSize: Int = 3
  val srcfile:String = ""

  override def configure(): Unit = {
//    bindConstant().annotatedWith(Names.named("DefaultSize")).to(defaultSize)
    bind[GameControllerInterface].to[controller.component.controllerBaseImpl.GameController]
  }

}
