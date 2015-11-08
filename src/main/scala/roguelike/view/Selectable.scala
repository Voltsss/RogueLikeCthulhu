package roguelike.view

/**
 * Created by volts on 15/10/29.
 */
trait Selectable {
  var cursor:Int = 0

  def cursorUp(maxIndex:Int):Unit = {
    cursor = if(cursor <= 0) maxIndex else cursor-1
  }

  def cursorDown(maxIndex:Int):Unit = {
    cursor = if(cursor >= maxIndex) 0 else cursor+1
  }

  def cursorDiside(list:Array[MenuItem]):() => Option[Menu] = {
    val err: Int => MenuItem = x => new MenuItem("none",() => {assert(true,"NONE MENU ITEM is Desided!!");None;})
    list.applyOrElse(cursor,err).f

  }


}
