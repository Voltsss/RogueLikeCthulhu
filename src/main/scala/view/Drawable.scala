package view


/**
 * Created by volts on 14/09/28.
 */
import model.Position
import model.param.Panel

trait Drawable {
  type Screen = Vector[Vector[Option[String]]]
  //case class Position(y:Int, x:Int)
  def draw( exScreen: Screen ) : Screen

  def overwritePositions ( pList : Array[Position], exScreen : Screen , char : Char) : Screen = {
    if ( pList.isEmpty ){
      exScreen
    }else{
      overwritePoint(pList.head,overwritePositions(pList.tail,exScreen,char),char)
      //overwritePositions(pList.tail,exScreen,char).updated(pList.head.x,exScreen(pList.head.x).updated(pList.head.y,Some(char.toString)))
    }
  }

  def overwritePoint( p:Position, exScreen:Screen,char:Char):Screen ={
    exScreen.updated(p.col,exScreen(p.col).updated(p.row,Some(char.toString)))
  }

  def screenEmpty ( width : Int , height: Int ) : Screen = {
    Vector.fill(width)(Vector.fill(height)(Option.empty[String]))
  }

  def screenFill ( width : Int , height: Int , str : String ) : Screen = {
    Vector.fill(width)(Vector.fill(height)( Some( str )))
  }

  def appearance(sc : Screen): Unit ={

  }
}
