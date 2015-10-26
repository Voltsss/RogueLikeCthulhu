package controller

import model._
import view._
import model.Position
import javafx.{scene => jfxs}
import javafx.scene.{input => jfxsi}
import model.DungeonGenerator

import scala.util.Random
import scalaz.Order

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

  var currentDungeon:Option[Floor] = None
  var player:Player = new Player()

  var currentLevelEnemies:List[Enemy] = List()
  var currentLevelItems:List[Item] = List()

  def setNewgame(): Unit ={
    player = new Player()
    // TODO floorlevel
    setNewFloor(0)
  }

  def setNewFloor(floorLevel : Int): Unit = {
    // floor の生成
    currentDungeon = floorLevel match {
      case 0 => Some(DungeonGenerator.makeTestDungeon)
      case _ => Some(DungeonGenerator.makeRandomDungeon(floorLevel))
    }

    // Character の配置
    val positionList : List[Position] = getRandomPositionList(currentDungeon match {case Some(f) => f case _ => DungeonGenerator.makeTestDungeon })
    player.setPosition(positionList(0))
    currentLevelEnemies = (for(enemyNum <- 1 to 30) yield {
      new Enemy(enemyKindID = 0,initPosition = positionList.apply(enemyNum), initLevel = 0)
    }).toList

    // Item の配置
    currentLevelItems = (for(itemNum <- 31 to 35) yield {
      new Item(itemKindID = 0,initPosition = positionList.apply(itemNum))
    }).toList


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
      case Up       =>  moveAndAttack(player,player.getPosition.offsetCopy(model.Up))
      case Down     =>  moveAndAttack(player,player.getPosition.offsetCopy(model.Down))
      case Right    =>  moveAndAttack(player,player.getPosition.offsetCopy(model.Right))
      case Left     =>  moveAndAttack(player,player.getPosition.offsetCopy(model.Left))
      case UpRight  =>  moveAndAttack(player,player.getPosition.offsetCopy(model.UpRight))
      case UpLeft   =>  moveAndAttack(player,player.getPosition.offsetCopy(model.UpLeft))
      case DownRight=>  moveAndAttack(player,player.getPosition.offsetCopy(model.DownRight))
      case DownLeft =>  moveAndAttack(player,player.getPosition.offsetCopy(model.DownLeft))
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

  def moveAndAttack(mover : Character, nextPosition : Position) = {
    if(Hit.isEnter(nextPosition)){
      mover.position = nextPosition
      if(Hit.isInItem(nextPosition)){
        Hit.getInItem(nextPosition) match {
          case Some(i) => takeItem(i)
          case _ =>
        }

      }
    }else if(Hit.isInCharacter(nextPosition)){
      Hit.getInCharacter(nextPosition) match {
        case Some(target)  => attack(mover, target)
        case _        =>
      }
    }
  }

  def attack(attacker : Character, target : Character): Unit ={
    /*DEBUG*/println(attacker.getName + " is Attacked " + target.getName + " OldHP: " + target.getHitpoint )
    val damage : Int = if(attacker.getAttack > target.getDefence) attacker.getAttack - target.getDefence else 0
    if (target.applyDamage(damage) == Dead){
      slay(attacker,target)
    }
    /*DEBUG*/println(" NewHP: " + target.getHitpoint)
  }

  def slay(attacker : Character, target: Character): Unit ={
    /*DEBUG*/println(attacker.getName + " slain " + target.getName + " !")
    currentLevelEnemies = currentLevelEnemies.filterNot((e : Enemy) => e eq target )
  }

  def takeItem(targetItem: Item): Unit = {
    /*DEBUG*/ println("GET ITEM!")
    currentLevelItems = currentLevelItems.filterNot((i : Item) => i eq targetItem)
  }
}


