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


  // TODO reimplementation to NEW API : setNewgame
  class TestPlayer extends Character {
    val position = Position(40,20)
    val cp = CharacterParameter()
    def draw (exScreen: Screen ) : Screen = {
      overwritePositions(Array(model.Position(position.y,position.x)),exScreen,'@')
    }
  }

  val testPlayer = new TestPlayer
  view.deprecation_setInGameController(this)

  def handleKeyInput(event : jfxs.input.KeyEvent){
    val inputKey:InputOrder = event.getCode() match {
      case jfxsi.KeyCode.Q      =>  UpRight
      case jfxsi.KeyCode.W      =>  Up
      case jfxsi.KeyCode.E      =>  UpLeft
      case jfxsi.KeyCode.D      =>  Right
      case jfxsi.KeyCode.S      =>  StayAttack
      case jfxsi.KeyCode.D      =>  Left
      case jfxsi.KeyCode.Z      =>  DownRight
      case jfxsi.KeyCode.X      =>  Down
      case jfxsi.KeyCode.C      =>  DownLeft
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

    println("testPlayerPosition" + testPlayer.getPosition)
  }

  def ingameKeyEvent(input:InputOrder): Unit ={
    input match {
      case Up       =>  testPlayer.moveUp
      case Right    =>  testPlayer.moveRight
      case Left     =>  testPlayer.moveLeft
      case Down     =>  testPlayer.moveDown
      case Menu     =>  menuOpen
      case _        =>  assert(false,"InGameInput : Undefined InputKey")
    }
  }

  def menuKeyEvent(input: InputOrder): Unit ={
    // TODO need menu API
    input match {
      case Menu   =>  menuClose
      case Up     =>  topMenuCursor = if(topMenuCursor-1 < 0) topMenuCursorMax else topMenuCursor-1
      case Down   =>  topMenuCursor = if(topMenuCursor+1 > topMenuCursorMax) 0 else topMenuCursor+1
      case Enter  =>  topMenuChoice = topMenuList(topMenuCursor) ; println("InGameController.menuKeyEvent:: MENU CHOICE=" + topMenuChoice)// 仮実装：選択内容に合わせて次の関数へのつなぎ
      case _      =>  assert(false,"Menu: Error : undefined input key")
    }
  }

  def menuOpen(): Unit ={
    // TODO need menu API
    topMenuMode=true
    println("Menu: menu open")
  }

  def menuClose(): Unit ={
    // TODO need menu API
    topMenuMode=false
    println("Menu: menu close")
  }

}


