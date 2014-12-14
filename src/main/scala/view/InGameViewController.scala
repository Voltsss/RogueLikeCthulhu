package view

import javafx.scene.{control => jfxsc}

import controller._
import model.DungeonGenerator
import model.param.panel._


class InGameViewController {
  private var viewLabel: jfxsc.Label = _
  private var igc: InGameController = _

  def setLabel(label: jfxsc.Label): Unit = {
    viewLabel = label
  }

  // TODO reimplement and delete
  def deprecation_setInGameController(controller: InGameController): Unit = {
    igc = controller
  }

  def drawViewText(): Unit = {
    // TODO get a data of dungeon
    val dungeonText : Array[Array[Option[String]]] = dungeonConvert(DungeonGenerator.makeTestDungeon)
    val topMenu : Menu = new Menu(igc.topMenuCursor,igc.topMenuList)

    val drawDungeon = igc.testPlayer.draw(dungeonText)

    val finalScreen = if(igc.menuMode){
      topMenu.draw(drawDungeon)
    }else{
      drawDungeon
    }

    viewLabel.setText(finalScreen
      .map {
      _.map { x => x.getOrElse(" ")
      }.mkString
    }.mkString("\n"))


  }


  //  val なになにがなになにの時 = (menuList.max.length + viewVal.tpX*2) > (viewText(0).size - viewVal.vmX*2)
  //  val menuWidth = if(なになにがなになにの時){
  //    viewText(0).size - viewVal.vmX * 2
  //  }else{
  //    menuList.max.length + viewVal.tpX * 2
  //  }
  //  val これこれがこれこれの時 = (menuList.size + viewVal.tpY * 2 ) > (viewText.size - viewVal.vmY*2)
  //  val menuHeight = if(これこれがこれこれの時) {
  //    viewText.size - viewVal.vmY * 2
  //  }else{
  //    menuList.max.length + viewVal.tpY * 2
  //  }


  implicit class PanelParam2String(p: Panel) {
    def appearance = p match {
      case Wall => "%"
      case Door => "+"
      case Way => "*"
      case Floor => "."
      case _ => " "
    }
  }

  private def dungeonConvert(dungeon: Array[Array[Panel]]): Array[Array[Option[String]]] = {
    dungeon.map { _.map( x => Option(x.appearance) ) }
  }


}

