package view

/**
 * Created by volts on 14/10/02.
 */

import model.Position
import model.param.Panel

object viewVal {
  val vmTop : Int = 3
  val vmBot : Int = 1
  val vmLeft : Int = 10
  val vmRight : Int = 1
  val tpTop : Int = 1
  val tpBot : Int = 1
  val tpLeft  : Int = 1
  val tpRight : Int = 1
  val cursorWidth : Int = 1
  val cursor : String = ">"
}

abstract sealed class MenuControl
case object TopMenu extends MenuControl
case object ItemMenu extends MenuControl


class Menu(menuControl: MenuControl,var menuList:Array[String]) extends Drawable {
  var cursor:Int = 0
  val cursorMax:Int = menuList.size-1
  if(menuList.isEmpty) menuList = Array("-")

  def cursorUp():Unit = {
    cursor = if(cursor <= 0) cursorMax else cursor-1
  }

  def cursorDown():Unit = {
    cursor = if(cursor >= cursorMax) 0 else cursor+1
  }

  //abstract def decide(): Unit

  def getMenuControl:MenuControl = menuControl
  def getMenuList:scala.collection.immutable.List[String] = menuList.toList

  def draw(exScreen:Screen):Screen = {
    //menuListOverWrite(frameOverWrite(exScreen))
    menuCursorOverWrite(menuListOverWrite(frameOverWrite(backOverWrite(exScreen))))
  }

  def calcMenuWidth(exScreen : Screen):Int = {
    if ((menuList.max.length + viewVal.tpLeft + viewVal.tpRight + viewVal.cursorWidth) > (exScreen(0).size - viewVal.vmLeft - viewVal.vmRight)) {
      exScreen(0).size - viewVal.vmLeft - viewVal.vmRight
    } else {
      menuList.max.length + viewVal.tpLeft + viewVal.tpRight + viewVal.cursorWidth
    }
  }

  def calcMenuHeight(exScreen : Screen):Int = {
    if ((menuList.size + viewVal.tpTop + viewVal.tpBot) > (exScreen.size - viewVal.vmTop - viewVal.vmBot)) {
      exScreen.size - viewVal.vmTop - viewVal.vmBot
    } else {
      menuList.size + viewVal.tpTop + viewVal.tpBot
    }
  }

  def backOverWrite (exScreen : Screen) : Screen = {
    val menuWidth = calcMenuWidth(exScreen)
    val menuHeight = calcMenuHeight(exScreen)
    val firstWidth = viewVal.vmLeft +1
    val firstHeight = viewVal.vmTop +1
    val spaceList = Array.fill(menuHeight)(" " * menuWidth)
    var screen = exScreen
    stringListOverWrite(exScreen,spaceList,firstWidth,firstHeight)
  }

  def frameOverWrite (exScreen : Screen) : Screen = {

    val menuWidth = calcMenuWidth(exScreen)
    val menuHeight = calcMenuHeight(exScreen)

    val topLine = (Array.range(viewVal.vmLeft ,viewVal.vmLeft+menuWidth+2) zip Stream.continually(viewVal.vmTop)).map(t=>Position(col=t._2,row=t._1))
    val buttomLine = (Array.range(viewVal.vmLeft,viewVal.vmLeft+menuWidth+2) zip Stream.continually(viewVal.vmTop+menuHeight)).map(t=>Position(col=t._2+1,row=t._1))
    val leftLine = (Array.range(viewVal.vmTop ,viewVal.vmTop+menuHeight+2) zip Stream.continually(viewVal.vmLeft)).map(t=>Position(col=t._1,row=t._2))
    val rightLine = (Array.range(viewVal.vmTop ,viewVal.vmTop+menuHeight+2) zip Stream.continually(viewVal.vmLeft+menuWidth)).map(t=>Position(col=t._1 ,row=t._2+1))
    val corner = Array(
      Position(row=viewVal.vmLeft,col=viewVal.vmTop),
      Position(row=viewVal.vmLeft+menuWidth+1,col=viewVal.vmTop),
      Position(row=viewVal.vmLeft,col=viewVal.vmTop+menuHeight+1),
      Position(row=viewVal.vmLeft+menuWidth+1,col=viewVal.vmTop+menuHeight+1))

    val topped = overwritePositions(topLine,exScreen,'-')
    val buttomed = overwritePositions(buttomLine,topped,'-')
    val lefted = overwritePositions(leftLine,buttomed,'|')
    val righted = overwritePositions(rightLine,lefted,'|')
    val framed = overwritePositions(corner,righted,'+')

    framed
  }

  def menuListOverWrite(exScreen:Screen):Screen={
    def stringOverWrite(str:String,index:Int,exArray:Vector[Option[String]]):Vector[Option[String]] = {
      if (str.size == 0) {
        exArray
      } else {
        val owArray = exArray.updated(index,Option(str.head.toString))
        stringOverWrite(str.tail,index+1,owArray)
      }
    }
    val firstWidth = viewVal.vmLeft + viewVal.tpLeft + viewVal.cursorWidth + 1
    val firstHeight = viewVal.vmTop + viewVal.tpTop +1
    var screen = exScreen
    for(menu <- menuList.zipWithIndex){
      screen = screen.updated(firstHeight+menu._2,stringOverWrite(menu._1,firstWidth,screen(firstHeight+menu._2)))
    }
    val returnScreen = screen
    returnScreen
  }

  def menuCursorOverWrite(exScreen:Screen):Screen = {
    def stringOverWrite(str:String,index:Int,exArray:Vector[Option[String]]):Vector[Option[String]] = {
      if (str.size == 0) {
        exArray
      } else {
        val owArray = exArray.updated(index,Option(str.head.toString))
        stringOverWrite(str.tail,index+1,owArray)
      }
    }
    val firstWidth = viewVal.vmLeft + viewVal.tpLeft + 1
    val firstHeight = viewVal.vmTop + viewVal.tpTop + 1
    //val menuCursorIndex_tmp = 0
    val screen = exScreen.updated(firstHeight+cursor,stringOverWrite(viewVal.cursor,firstWidth,exScreen(firstHeight+cursor)))
    screen
  }


  def stringListOverWrite(exScreen:Screen,strList:Array[String],paddingX:Int,paddingY:Int):Screen={
    def stringOverWrite(str:String,index:Int,exArray:Vector[Option[String]]):Vector[Option[String]] = {
      if (str.size == 0) {
        exArray
      } else {
        val owArray = exArray.updated(index,Option(str.head.toString))
        stringOverWrite(str.tail,index+1,owArray)
      }
    }
    var screen = exScreen
    for(menu <- strList.zipWithIndex){
      screen = screen.updated(paddingY+menu._2,stringOverWrite(menu._1,paddingX,screen(paddingY+menu._2)))
    }
    val returnScreen = screen
    returnScreen
  }

}
