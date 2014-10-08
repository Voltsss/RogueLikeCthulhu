package view

/**
 * Created by volts on 14/10/02.
 */

object viewVal {
  val vmX: Int = 5
  val vmY: Int = 3
  val tpX: Int = 1
  val tpY: Int = 1
}

class Menu extends Drawable {
  import scalaz._
  val menuListTest = Array("test1","test2","test3")

  def draw(exScreen:Screen):Screen = {
    menuListOverWrite(frameOverWrite(exScreen))
  }

  def frameOverWrite (exScreen : Screen) : Screen={
    val menuWidth = if ((menuListTest.max.length + viewVal.tpX * 2) > (exScreen(0).size - viewVal.vmX * 2)) {
      exScreen(0).size - viewVal.vmX * 2
    } else {
      menuListTest.max.length + viewVal.tpX * 2
    }
    val menuHeight = if ((menuListTest.size + viewVal.tpY * 2) > (exScreen.size - viewVal.vmY * 2)) {
      exScreen.size - viewVal.vmY * 2
    } else {
      menuListTest.size + viewVal.tpY * 2
    }

    val topLine = (Array.range(viewVal.vmX ,viewVal.vmX+menuWidth+1) zip Stream.continually(viewVal.vmY)).map(t=>DummyPosition(x=t._2,y=t._1))
    val buttomLine = (Array.range(viewVal.vmX,viewVal.vmX+menuWidth+1) zip Stream.continually(viewVal.vmY+menuHeight)).map(t=>DummyPosition(x=t._2,y=t._1))
    val leftLine = (Array.range(viewVal.vmY ,viewVal.vmY+menuHeight+1) zip Stream.continually(viewVal.vmX)).map(t=>DummyPosition(x=t._1,y=t._2))
    val rightLine = (Array.range(viewVal.vmY ,viewVal.vmY+menuHeight+1) zip Stream.continually(viewVal.vmX+menuWidth)).map(t=>DummyPosition(x=t._1,y=t._2))
    val corner = Array(
      DummyPosition(viewVal.vmX,viewVal.vmY),
      DummyPosition(viewVal.vmX+menuWidth,viewVal.vmY),
      DummyPosition(viewVal.vmX,viewVal.vmY+menuHeight),
      DummyPosition(viewVal.vmX+menuWidth,viewVal.vmY+menuHeight))

    val topped = overwritePositions(topLine,exScreen,'-')
    val buttomed = overwritePositions(buttomLine,topped,'-')
    val lefted = overwritePositions(leftLine,buttomed,'|')
    val righted = overwritePositions(rightLine,lefted,'|')
    val framed = overwritePositions(corner,righted,'+')

    framed
  }

  def menuListOverWrite(exScreen:Screen):Screen={
    def stringOverWrite(str:String,index:Int,exArray:Array[Option[String]]):Array[Option[String]] = {
      if (str.size == 0) {
        exArray
      } else {
        val owArray = exArray.updated(index,Option(str.head.toString))
        stringOverWrite(str.tail,index+1,owArray)
      }
    }
    val firstWidth = viewVal.vmX + viewVal.tpX
    val firstHeight = viewVal.vmY + viewVal.tpY
    var screen = exScreen
    for(menu <- menuListTest.zipWithIndex){
      screen = screen.updated(firstHeight+menu._2,stringOverWrite(menu._1,firstWidth,screen(firstHeight+menu._2)))
    }
    val returnScreen = screen
    returnScreen
  }

}
