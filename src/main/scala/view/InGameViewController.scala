package view

import javafx.scene.{control => jfxsc}
import model.DungeonGenerator
import model.param.PanelParam._
import controller._

class InGameViewController {
  private var viewLabel: jfxsc.Label = _
  private var igc: InGameController = _

  def setLabel(label: jfxsc.Label): Unit ={
    viewLabel = label
  }

  // TODO reimplement and delete
  def deprecation_setInGameController(controller:InGameController): Unit ={
    igc = controller
  }

  def drawViewText(): Unit ={
    val dungeon = dungeonConvert(DungeonGenerator.makeTestDungeon)
    viewLabel.setText(atPlayer(dungeon).map {_.mkString}.mkString("\n"))
  }

  private def atPlayer(dungeon:Array[Array[String]]):Array[Array[String]] = {
    // TODO player.getPosition
    dungeon(igc.testPlayer.getPosition._2)(igc.testPlayer.getPosition._1) = "@"
    dungeon
  }

  implicit class PanelParam2String(p: Panel) {
    def appearance = p match {
      case Wall  => "%"
      case Door  => "+"
      case Way   => "*"
      case Floor => "."
      case _     => " "
    }
  }

  private def dungeonConvert(dungeon:Array[Array[Panel]]):Array[Array[String]] = {
    dungeon.map {_.map(_.appearance)}
  }


}

