package roguelike.model

import roguelike.controller.InGameController

/**
 * Created by volts on 15/04/19.
 */
object Hit {
  //var currentFloor:Option[Floor] = None
  //var characterList:List[Character] = Nil

  //def setFloor(floor:Floor) = currentFloor = Some(floor)
  //def setCharacterList(cList : List[Character]) = characterList = cList

  def isEnter(position:Position):Boolean = {
    InGameController.currentDungeon match {
      case Some(f) =>
        f.isEnter(position) match {
          case true => !isInCharacter(position)
          case false => false
        }
      case _ =>
        false
    }
  }

  def isInCharacter(position: Position) : Boolean = {
    (InGameController.player +: InGameController.currentLevelEnemies).count(c => c.getPosition.equals(position)) >= 1
  }

  def getInCharacter(position: Position) : Option[Character]= {
    val positionedCharacters : List[Character] =
      (InGameController.player +: InGameController.currentLevelEnemies).filter(c => c.getPosition.equals(position))
    assert(positionedCharacters.size <= 1,"getInCharacterEnemy:指定された座標に複数のCharacterがいます")
    positionedCharacters.headOption
  }

  def isInItem(position: Position) : Boolean = {
    (InGameController.currentLevelItems).count(i => i.getPosition.equals(position)) >= 1
  }
  def getInItem(position: Position) : Option[Item] = {
    val positionedItem : List[Item] =
      (InGameController.currentLevelItems).filter(i => i.getPosition.equals(position))
    assert(positionedItem.size <= 1,"getInCharacterEnemy:指定された座標に複数のCharacterがいます")
    positionedItem.headOption
  }


}
