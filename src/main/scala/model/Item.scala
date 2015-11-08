package model

import view.Drawable

/**
 * Created by volts on 15/10/27.
 */
class Item(
          itemKindID : Int = 0,
          initPosition : Position = Position(40,18)
            ) extends Drawable{
  val position:Position = initPosition
  val char:Char = ItemGenerator.getChar(itemKindID)
  val name:String = ItemGenerator.getName(itemKindID)
  val itemID:Int = itemKindID
  val weaponEquipable : Boolean = ItemGenerator.getWeaponEquipable(itemKindID)

  def draw (exScreen: Screen): Screen =
  {
    overwritePositions(Array(model.Position(position.row, position.col)), exScreen, char)
  }

  def getPosition : Position = position

}

object ItemGenerator{
  def getChar(itemKindID : Int): Char = {
    itemKindID match {
      case 0 => '/'
      case _ => '|'
    }
  }
  def getName(itemKindID : Int): String = {
    itemKindID match {
      case 0 => "HinokiStick"
      case _ => "DEBUG ITEM"
    }
  }

  def getWeaponEquipable(itemKindID : Int) : Boolean = {
    itemKindID match {
      case 0 => true
      case _ => false
    }
  }
}