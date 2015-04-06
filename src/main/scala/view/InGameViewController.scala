package view

import javafx.scene.{control => jfxsc}

import controller._
import model.{FloorLens, DungeonGenerator, Floor}
import model.param.Panel
import scala.collection.mutable._

class InGameViewController extends FloorLens {
  private var viewLabel: jfxsc.Label = _
  private var igc: InGameController = _
  private var menuStack: Stack[Menu] = Stack[Menu]()
  private val topMenuList = Array("item_tmp","status_tmp","option_tmp","debug_tmp")

  def setLabel(label: jfxsc.Label): Unit = {
    viewLabel = label
  }

  // TODO reimplement and delete
  def deprecation_setInGameController(controller: InGameController): Unit = {
    igc = controller
  }

  def topMenuOpen(): Unit = {
    menuStack.push(new Menu(topMenuList))
  }

  def topMenuClose(): Unit = {
    menuStack.clear()
  }

  def cursorUp(): Unit = {
    menuStack.head.cursorUp()

  }

  def cursorDown(): Unit = {
    menuStack.head.cursorDown()
  }

  def decide(): Unit = {
    //TODO
    println(s"InGameViewController:MENU DECIDE:${topMenuList(menuStack.head.cursor)}")
  }

  def drawViewText(): Unit = {
    // TODO get a data of dungeon
    val dungeonText : Vector[Vector[Option[String]]] = dungeonConvert(DungeonGenerator.makeTestDungeon)
    val topMenu : Menu = new Menu(igc.topMenuList)

    val drawDungeon = igc.player.draw(dungeonText)

//    val finalScreen = if(igc.topMenuMode){
//      topMenu.draw(drawDungeon)
//    }else{
//      drawDungeon
//    }

    val finalScreen = if(menuStack.isEmpty){
      drawDungeon
    }else{
      menuStack.toList.foldRight(drawDungeon)((n,z)=> n.draw(z))
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
      case Panel.Wall  => "%"
      case Panel.Door  => "+"
      case Panel.Way   => "*"
      case Panel.Floor => "."
      case _           => " "
    }
  }

  private def dungeonConvert(floor: Floor): Vector[Vector[Option[String]]] = {
    floor.data.map { _.map( x => Option(x.appearance) ) }
  }


}

