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
          case true => !isEnemy(position)
          case false => false
        }
      case _ =>
        false
    }
  }

  def isEnemy(position: Position) : Boolean = {
    characterList.count(c => c.getPostion.equals(position)) >= 1
  }

  def getEnemy(position: Position) : Option[Character]= {
    val positionedCharacters : List[Character] = characterList.filter(c => c.getPostion.equals(position))
    assert(positionedCharacters.size <= 1,"isEnemy:指定された座標に複数のCharacterがいます")
    positionedCharacters.headOption
  }


}
