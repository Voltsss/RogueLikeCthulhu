package model

import scala.io.Source
import model.panel._
import model.panel.PanelTypeImplicits._

object DungeonGenerator {
  val replaceRoguePanel: String => Array[PanelType] = (str: String) => {
    str map {
      (_: Char) match {
        case '1' => Floor
        case '2' => Wall
        case '3' => Door
        case '4' => Way
        case _   => NothingPanel
      }
    }
  }.toArray

  private def makeDungeon(fileName: String): Array[Array[PanelType]] = {
    val file = getClass.getResourceAsStream(fileName)
    Source.fromInputStream(file).getLines.map(replaceRoguePanel).toArray
  }

  // Test Dungeon
  def makeTestDungeon = makeDungeon("/test_dungeon.dun")

  // TODO: Randomize Dungeon
  def makeRandomDungeon = Array.ofDim[PanelType](1,0)

  implicit class dungeon2String(dungeon: Array[Array[PanelType]]) {
    def toAppearance = dungeon.map {_.map(_.appearance).mkString}.mkString("\n")
  }
}
