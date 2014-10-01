package view

import javafx.scene.{control => jfxsc}

import controller._
import model.DungeonGenerator
import model.param.PanelParam._

object viewVal {
  val vmX: Int = 5
  val vmY: Int = 3
  val tpX: Int = 3
  val tpY: Int = 3
}

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
    val dungeonText : Array[Array[Option[String]]] = dungeonConvert(DungeonGenerator.makeTestDungeon)

    //    if (igc.menuMode == true){
    //      viewLabel.setText(menuOverWrite(atPlayer(dungeonText)).map {_.mkString}.mkString("\n"))
    //    }else{
    //      viewLabel.setText((atPlayer(dungeonText)).map { _.mkString}.mkString("\n"))
    //    }

    //    val text =if (igc.menuMode){
    //      menuOverWrite(atPlayer(dungeonText)).map {_.mkString}.mkString("\n")
    //    }else{
    //      atPlayer(dungeonText).map { _.mkString}.mkString("\n")
    //    }
    //    viewLabel.setText(text)


    viewLabel.setText(igc.testPlayer.draw(dungeonText)
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

  def menuOverWrite(viewText: Array[Array[String]]): Array[Array[String]] = {
    // TODO menu function list
    //

    val xMax = viewText(0).size - viewVal.vmX
    val yMax = viewText.size - viewVal.vmY

    val menuList: Array[String] = Array("test1", "test2", "test3")


    val menuWidth = if ((menuList.max.length + viewVal.tpX * 2) > (viewText(0).size - viewVal.vmX * 2)) {
      viewText(0).size - viewVal.vmX * 2
    } else {
      menuList.max.length + viewVal.tpX * 2
    }
    val menuHeight = if ((menuList.size + viewVal.tpY * 2) > (viewText.size - viewVal.vmY * 2)) {
      viewText.size - viewVal.vmY * 2
    } else {
      menuList.max.length + viewVal.tpY * 2
    }
    // TODO 中断

    // TODO Delete
    viewText(20)(40) = "m"
    viewText(20)(41) = "e"
    viewText(20)(42) = "n"
    viewText(20)(43) = "u"
    viewText
  }


  private def atPlayer(dungeon: Array[Array[String]]): Array[Array[String]] = {
    // TODO player.getPosition
    dungeon(igc.testPlayer.getPosition._2)(igc.testPlayer.getPosition._1) = "@"
    dungeon
  }

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

