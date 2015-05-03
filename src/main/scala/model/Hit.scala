package model

/**
 * Created by volts on 15/04/19.
 */
object Hit {
  var currentFloor:Option[Floor] = None
  var characterList:List[Character] = Nil

  def setFloor(floor:Floor) = currentFloor = Some(floor)
  def setCharacterList(cList : List[Character]) = characterList = cList

  def isEnter(position:Position):Boolean = {
    currentFloor match {
      case Some(f) =>
        f.isEnter(position) match {
          case true => characterList.count(c => c.getPostion.equals(position)) == 0
          case false => false
        }
      case _ =>
        false
    }
  }


}
