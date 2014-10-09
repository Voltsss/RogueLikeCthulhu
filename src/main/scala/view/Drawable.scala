package view


/**
 * Created by volts on 14/09/28.
 */
trait Drawable {
  type Screen = Array[Array[Option[String]]]
  case class DummyPosition(y:Int, x:Int)
  def draw( exScreen: Screen ) : Screen

  def overwritePositions ( pList : Array[DummyPosition], exScreen : Screen , char : Char) : Screen = {
    if ( pList.isEmpty ){
      exScreen
    }else{
      overwritePoint(pList.head,overwritePositions(pList.tail,exScreen,char),char)
      //overwritePositions(pList.tail,exScreen,char).updated(pList.head.x,exScreen(pList.head.x).updated(pList.head.y,Some(char.toString)))
    }
  }

  def overwritePoint( p:DummyPosition, exScreen:Screen,char:Char):Screen ={
    exScreen.updated(p.x,exScreen(p.x).updated(p.y,Some(char.toString)))
  }

  def screenEmpty ( width : Int , height: Int ) : Screen = {
    Array.fill(width)(Array.fill(height)(Option.empty[String]))
  }

  def screenFill ( width : Int , height: Int , str : String ) : Screen = {
    Array.fill(width)(Array.fill(height)( Some( str )))
  }

  def appearance(sc : Screen): Unit ={

  }
  

}
