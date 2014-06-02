package model

import scala.io.Source
import model.panel._

object DungeonGenerator {

  type DungeonMap = Vector[Vector[RoguePanel]]

  val replaceRoguePanel = (str: String) => {
    str map {
      (_: Char) match {
        case '1' => RoguePanel(Floor, true)
        case '2' => RoguePanel(Wall)
        case '3' => RoguePanel(Door, true)
        case '4' => RoguePanel(Way, true)
        case _ => RoguePanel(NothingPanel)
      }
    }
  }

  def makeDungeon(fileName: String) = {
    Source.fromFile(fileName).getLines.map(replaceRoguePanel).toVector
  }
}