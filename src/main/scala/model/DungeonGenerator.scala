package roguelike.model

import scala.io.Source
import roguelike.model.param.Panel

trait Generatable {
  def make: Floor
}

trait GeneratorFromFile extends Generatable {

  val fileName: String

  private val replaceTable: Map[String, Panel] = Map(
    "1" -> Panel.Floor,
    "2" -> Panel.Wall,
    "3" -> Panel.Door,
    "4" -> Panel.Way
  )

  private def replaceChar2Panel(str: String): Vector[Panel] =
    str.map{(c: Char) => replaceTable.getOrElse(c.toString, Panel.NothingPanel)}.toVector

  def make: Floor = {
    val file = getClass.getResourceAsStream(fileName)
    new Floor(Source.fromInputStream(file).getLines.map(replaceChar2Panel).toVector)
  }
}

class TestDungeon extends GeneratorFromFile {
  val fileName = "/test_dungeon.dun"
}


// If you create dungeon, use this object.
object DungeonGenerator {

  def makeTestDungeon = new TestDungeon make

  def makeRandomDungeon(floorLevel: Int) = new TestDungeon make

  implicit class dungeon2String(dungeon: Vector[Vector[Panel]]) {
    def toAppearance: String = dungeon.map {_.map(_.appearance).mkString}.mkString("\n")
  }
}
