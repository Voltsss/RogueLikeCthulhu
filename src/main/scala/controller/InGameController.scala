package controller

import model._

import view._
import javafx.{scene => jfxs}

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
  class TestPlayer extends Character {
    val position = Position(20,40)
    val cp = CharacterParameter()
  }
  val testPlayer = new TestPlayer


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
      case _    =>  NoneInput
    }

    inputKey match {
      case Up       =>  testPlayer.moveUp
      case Right    =>  testPlayer.moveRight
      case Left     =>  testPlayer.moveLeft
      case Down     =>  testPlayer.moveDown
      case _        =>  
    }

    inputKey match {
      case UpRight  =>  println("IngameController:UpRight")
      case Up       =>  println("IngameController:Up")
      case UpLeft   =>  println("IngameController:UpLeft")
      case Right    =>  println("IngameController:Rignt")
      case StayAttack=> println("IngameController:StayAttack")
      case Left     =>  println("IngameController:Left")
      case DownRight=>  println("IngameController:DownRight")
      case Down     =>  println("IngameController:Down")
      case DownLeft =>  println("IngameController:DownLeft")
      case NoneInput=>  println("IngameController:NonInput")
      case _        =>  println("IngameController:Error")

    }

    println("testPlayerPosition" + testPlayer.getPosition)
  }

    
}


