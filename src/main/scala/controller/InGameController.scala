package controller

import model._

import view._
import javafx.{scene => jfxs}
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

  var menuMode:Boolean = false

  // TODO reimplementation to NEW API : setNewgame
  class TestPlayer extends Character {
    val position = Position(40,20)
    val cp = CharacterParameter()
    def draw (exScreen: Screen ) : Screen = {
      overwritePositions(Array(DummyPosition(position.x,position.y)),exScreen,'@')
    }
  }

  val testPlayer = new TestPlayer
  view.deprecation_setInGameController(this)

  def handleKeyInput(event : jfxs.input.KeyEvent){
    val inputKey:InputOrder = event.getCharacter() match {
      case "q"  =>  UpRight
      case "w"  =>  Up
      case "e"  =>  UpLeft
      case "a"  =>  Right
      case "s"  =>  StayAttack
      case "d"  =>  Left
      case "z"  =>  DownRight
      case "x"  =>  Down
      case "c"  =>  DownLeft
      case "m"  =>  Menu
      case _    =>  NoneInput
    }

    if(menuMode == true){
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
      case _      =>  assert(false,"Menu: Error : undefined input key")
    }
  }

  def menuOpen(): Unit ={
    // TODO need menu API
    menuMode=true
    println("Menu: menu open")
  }

  def menuClose(): Unit ={
    // TODO need menu API
    menuMode=false
    println("Menu: menu close")
  }

}


