package roguelike.view

import javafx.scene.{control => jfxsc}

import roguelike.controller._
import roguelike.model.{Enemy, DungeonGenerator, Floor}
import roguelike.model.param.Panel
import scala.collection.mutable._

object InGameViewController {
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
    val err : () => Option[Menu] = () => {assert(true,"NONE MENU ITEM is Desided!!");None}
    val names = Array("item_tmp","status_tmp","option_tmp","debug_tmp")
    def itemMenuOpen(): () => Option[Menu] = {
      () => Some(new Menu(InGameController.player.inventory.map(item => new MenuItem(item.name,err)).toArray))
    }
    val list = Array() :+ (new MenuItem("item_tmp" , itemMenuOpen())) :+ (new MenuItem("status_tmp" , err)):+ (new MenuItem("option_tmp" , err)):+ (new MenuItem("debug_tmp" , err))
    menuStack.push(new Menu(list))
  }

  def topMenuClose(): Unit = {
    menuStack.clear()
  }

  def cursorUp(): Unit = {
    menuStack.head.cursorUp(menuStack.head.menuList.size)

  }

  def cursorDown(): Unit = {
    menuStack.head.cursorDown(menuStack.head.menuList.size)
  }

  def decide(): Unit = {
    //TODO
    //println(s"InGameViewController:MENU DECIDE:${topMenuList(menuStack.head.cursor)}")

    menuStack.head.cursorDiside(menuStack.head.menuList).apply() match {
      case Some(m) => menuStack.push(m)//menuStack.push(menuStack.head.cursorDiside(menuStack.head.menuList).get)
      case None => assert(true,"!!")
    }

//    menuStack.head.getMenuControl match {
//      case ItemMenu => println(s"InGameViewController:ItemMenu DECIDE:${menuStack.head.getMenuList(menuStack.head.cursor)}")
//    }
  }

  def cancel(): Unit = {
    menuStack.pop()
    if (menuStack.isEmpty) InGameController.menuClose
  }

  def drawViewText(): Unit = {
    // TODO get a data of dungeon
    val dungeonText : Vector[Vector[Option[String]]] = dungeonConvert(DungeonGenerator.makeTestDungeon)
    val itemText = InGameController.currentLevelItems.foldRight(dungeonText)((n,z)=> n.draw(z))
    val characterText = (InGameController.player +: InGameController.currentLevelEnemies).foldRight(itemText)((n,z)=> n.draw(z))
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
