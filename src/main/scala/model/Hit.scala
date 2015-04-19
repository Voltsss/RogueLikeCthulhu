package model

/**
 * Created by volts on 15/04/19.
 */
object Hit {
  var currentFloor:Option[Floor] = None
  def setFloor(floor:Floor) = currentFloor = Some(floor)

  def isEnter(position:Position):Boolean = {
    currentFloor match {
      case Some(f) =>
        f.isEnter(position)
      case _ =>
        false
    }
  }


}
