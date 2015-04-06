package controller

import model._
import view._
import model.Position
import javafx.{scene => jfxs}
import javafx.scene.{input => jfxsi}
import model.DungeonGenerator

abstract sealed class InputOrder
case object Up extends InputOrder
case object Down extends InputOrder
case object Right extends InputOrder
case object Left extends InputOrder
case object UpRight extends InputOrder
case object UpLeft extends InputOrder
case object DownRight extends InputOrder
case object DownLeft extends InputOrder
case object Enter extends InputOrder
case object Cancel extends InputOrder
case object Menu extends InputOrder
case object StayAttack extends InputOrder
case object NoneInput extends InputOrder

class InGameController(view: InGameViewController) {

  var topMenuMode:Boolean = false

  val topMenuList = Array("item_tmp","status_tmp","option_tmp","debug_tmp")
  var topMenuCursor:Int = 0
  val topMenuCursorMax = 3

  var topMenuChoice = ""

  private var current_dungeon:Floor = null
  var player:Player = new Player()
  def setNewgame(): Unit ={
    //TODO Create dungeon of 1st level
    current_dungeon = DungeonGenerator.makeTestDungeon
    //TODO Create initialized PlayerCharacter
    player = new Player()
  }
  view.deprecation_setInGameController(this)

  def handleKeyInput(event : jfxs.input.KeyEvent){
    val inputKey:InputOrder = event.getCode() match {
      case jfxsi.KeyCode.Q      =>  UpLeft
      case jfxsi.KeyCode.W      =>  Up
      case jfxsi.KeyCode.E      =>  UpRight
      case jfxsi.KeyCode.A      =>  Left
      case jfxsi.KeyCode.S      =>  StayAttack
      case jfxsi.KeyCode.D      =>  Right
      case jfxsi.KeyCode.Z      =>  DownLeft
      case jfxsi.KeyCode.X      =>  Down
      case jfxsi.KeyCode.C      =>  DownRight
      case jfxsi.KeyCode.M      =>  Menu
      case jfxsi.KeyCode.ENTER  =>  Enter
      case _    =>  NoneInput
    }

    if(topMenuMode == true){
      menuKeyEvent(inputKey)
    }else{
      ingameKeyEvent(inputKey)
    }

    view.drawViewText()

    println("testPlayerPosition" + player.getPosition)
  }

  def ingameKeyEvent(input:InputOrder): Unit ={
    input match {
      case Up       =>  player.moveUp
      case Right    =>  player.moveRight
      case Left     =>  player.moveLeft
      case Down     =>  player.moveDown
      case Menu     =>  {
        topMenuMode=true
        view.topMenuOpen()
      }
      case _        =>  assert(false,"InGameInput : Undefined InputKey")
    }
  }

  def menuKeyEvent(input: InputOrder): Unit ={
    // TODO need menu API
    input match {
      case Menu   =>  {
        topMenuMode = false
        view.topMenuClose()
      }
      case Up     =>  view.cursorUp()
      case Down   =>  view.cursorDown()
      case Enter  =>  view.decide()
      case _      =>  assert(false,"Menu: Error : undefined input key")
    }
  }

}


