package de.felite.util.fileIOComponent.fileIOxml

import java.io._

import com.google.inject.Guice
import com.google.inject.name.Names
import de.felite.FEliteModule
import de.felite.controller.GameControllerInterface
import de.felite.model.entity.obstacle.{Rock, Tree}
import de.felite.model.{DefEntity, Field, SoldierFactory}
import de.felite.util.fileIOComponent.FileIOInterface
import net.codingwell.scalaguice.InjectorExtensions._

import scala.xml.{Elem, PrettyPrinter}

class FileIO extends FileIOInterface {
  override def load(controller: GameControllerInterface): Field = {
    var field: Field = null
    val file = scala.xml.XML.loadFile("field.xml")
    val size = (file \\ "field" \ "@size").text.toInt
    val injector = Guice.createInjector(new FEliteModule)
    size match {
      case 4 => field = injector.instance[Field](Names.named("Small"))
      case 5 => field = injector.instance[Field](Names.named("Middle"))
      case 6 => field = injector.instance[Field](Names.named("Max"))
      case _ =>
    }

    val cellNode = (file \\ "cell")
    for (cell <- cellNode) {
      val y: Int = (cell \ "@row").text.toInt
      val x: Int = (cell \ "@col").text.toInt
      val typ: String = cell.text.trim
      typ match {
        case "a" =>
        case "s" =>
          val health: Int = (cell \ "@health").text.toInt
          val player: String = (cell \ "@player").text
          val owner = if (player == "p1") controller.player1
          else controller.player2

          val troop = SoldierFactory.create(typ = typ.charAt(0),
            pos = (x, y), health = health,
            player = owner)

          field.setCell(troop, x, y)
          owner.addPlayerTroop(troop)

        case "r" => field.setCell(Rock, x, y)
        case "t" => field.setCell(Tree, x, y)
        case _ => field.setCell(DefEntity, x, y) // default
      }
    }
    controller.field = field
    field
  }

  override def store(field: Field): Unit = {
    val pw = new PrintWriter(new File("field.xml"))
    val pp = new PrettyPrinter(120, 4)
    val xml = pp.format(fieldToXML(field), scala.xml.TopScope)
    pw.write(xml)
    pw.close()
  }

  def fieldToXML(field: Field): Elem = {
    <field size={field.getScale.toString}>
      {for {
      y <- 0 until field.getScale
      x <- 0 until field.getScale
    } yield  (field.getCell(x,y).toXML)
      //if (field.getCell(x, y).isInstanceOf[Troop]) troopToXML(field, x, y)
    //else obstacleToXML(field, x, y)}
      }
    </field>
  }
}
