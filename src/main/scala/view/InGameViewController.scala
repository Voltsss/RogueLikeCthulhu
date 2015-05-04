package view

import javafx.scene.{control => jfxsc}

import controller._
import model.{Enemy, FloorLens, DungeonGenerator, Floor}
import model.param.Panel
import scala.collection.mutable._

object InGameViewController extends FloorLens {
  private var viewLabel: jfxsc.Label = _
  private var menuStack: Stack[Menu] = Stack[Menu]()
  private val topMenuList = Array("item_tmp","status_tmp","option_tmp","debug_tmp")

  def setLabel(label: jfxsc.Label): Unit = {
    viewLabel = label
  }

//  // TODO reimplement and delete
//  def deprecation_setInGameController(controller: InGameController): Unit = {
//    igc = controller
//  }

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

    val characterText = (InGameController.player +: InGameController.currentLevelEnemies).foldRight(dungeonText)((n,z)=> n.draw(z))

    val finalText = if(menuStack.isEmpty){
      characterText
    }else{
      menuStack.toList.foldRight(characterText)((n,z)=> n.draw(z))
    }

    viewLabel.setText(finalText
      .map {
      _.map { x => x.getOrElse(" ")
      }.mkString
    }.mkString("\n"))


  }


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

