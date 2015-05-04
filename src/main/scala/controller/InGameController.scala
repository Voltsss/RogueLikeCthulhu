package controller

import model._
import view._
import model.Position
import javafx.{scene => jfxs}
import javafx.scene.{input => jfxsi}
import model.DungeonGenerator

import scala.util.Random

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

object InGameController {

  var topMenuMode:Boolean = false

  val topMenuList = Array("item_tmp","status_tmp","option_tmp","debug_tmp")
  var topMenuCursor:Int = 0
  val topMenuCursorMax = 3

  var topMenuChoice = ""

  private var current_dungeon:Floor = null
  var player:Player = new Player()

  var currentLevelEnemies:List[Enemy] = null

  def setNewgame(): Unit ={
    player = new Player()
    // TODO floorlevel
    setNewFloor(0)
  }

  def setNewFloor(floorLevel : Int): Unit = {
    // floor の生成
    current_dungeon = floorLevel match {
      case 0 => DungeonGenerator.makeTestDungeon
      case _ => DungeonGenerator.makeRandomDungeon(floorLevel)
    }
    // floor を Hit へ登録
    Hit.setFloor(current_dungeon)

    // Character の配置
    val positionList : List[Position] = getRandomPositionList(current_dungeon)
    player.setPosition(positionList(0))
    currentLevelEnemies = (for(enemyNum <- 1 to 30) yield {
      new Enemy(enemyKindID = 0,initPosition = positionList.apply(enemyNum), initLevel = 0)
    }).toList

    // Character を Hit へ登録
    Hit.setCharacterList(player +: currentLevelEnemies)

  }

  def getRandomPositionList(dungeon: Floor): List[Position]= {
    Random.shuffle(
        for(row <- 0 to dungeon.getHeight()-1; col <- 0 to dungeon.getWidth()-1) yield Position(col = col,row = row)
      ).toVector.filter(
      (p : Position) => {
        dungeon.getPanel (p.col, p.row) match {
          case model.param.Panel.Floor => true
          case _ => false
        }
      }
    ).toList
  }


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

    InGameViewController.drawViewText()

    println("testPlayerPosition" + player.getPositionNum)
  }

  def ingameKeyEvent(input:InputOrder): Unit ={
    input match {
      case Up       =>  player.moveUp
      case Right    =>  player.moveRight
      case Left     =>  player.moveLeft
      case Down     =>  player.moveDown
      case UpRight  =>  player.moveUpRight
      case UpLeft   =>  player.moveUpLeft
      case DownRight=>  player.moveDownRight
      case DownLeft =>  player.moveDownLeft
      case Menu     =>  {
        topMenuMode=true
        InGameViewController.topMenuOpen()
      }
      case _        =>  assert(false,"InGameInput : Undefined InputKey")
    }
  }

  def menuKeyEvent(input: InputOrder): Unit ={
    // TODO need menu API
    input match {
      case Menu   =>  {
        topMenuMode = false
        InGameViewController.topMenuClose()
      }
      case Up     =>  InGameViewController.cursorUp()
      case Down   =>  InGameViewController.cursorDown()
      case Enter  =>  InGameViewController.decide()
      case _      =>  assert(false,"Menu: Error : undefined input key")
    }
  }

}


