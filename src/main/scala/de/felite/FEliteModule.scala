package de.felite

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import de.felite.controller.GameControllerInterface
import de.felite.controller.component.controllerBaseImpl
import net.codingwell.scalaguice.ScalaModule

class FEliteModule extends AbstractModule with ScalaModule {

  val defSize: Int = 4

  override def configure(): Unit = {
    bindConstant().annotatedWith(Names.named("DefaultSize")).to(defSize)
    //bind[Field].to[Field] // ERROR selfbinding
    bind[GameControllerInterface].to[controllerBaseImpl.GameController]
    //bind[Field].annotatedWithName("test").toInstance(new Field(defSize))
    //bind[Field].annotatedWithName("middle").toInstance(new Field(5))
    //bind[Field].annotatedWithName("max").toInstance(new Field(6))
  }
}