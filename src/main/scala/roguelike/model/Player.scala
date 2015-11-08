package roguelike.model



/**
 * Created by volts on 15/04/06.
 */

abstract sealed class TakeItemProperty
case object Success extends TakeItemProperty
case object InventoryFull extends TakeItemProperty
//case object PossessionMax extends TakeItemProperty


abstract sealed class EquipItemProperty
case object EquipSuccess extends EquipItemProperty
case object UnknownFail extends EquipItemProperty

class Player extends Character{
  var position:Position = Position(40,20)
  val charaParam = CharacterParameter(
    name         = "Player",
    viewChar     = '@',
    experience   = 0,
    hitpoint     = 100,
    attack       = 20,
    defence      = 3,
    agility      = 10,
    dexterity    = 10,
    sanity       = 10
  )

  var inventory : List[Item] = List()
  val inventoryMaxSize = 10

  var equipWeapon : Item = null

  def draw (exScreen: Screen ) : Screen = {
    overwritePositions(Array(Position(position.row,position.col)),exScreen,'@')
  }

  def tryTakeItem (item : Item) : TakeItemProperty = {
    if(canAddItemToInventory){
      addItemInventory(item)
      Success
    }else{
      InventoryFull
    }
  }

  private def addItemInventory(item: Item) = {
    if(canAddItemToInventory){
      inventory = item +: inventory
    }
  }

  private def canAddItemToInventory: Boolean = {
    inventory.size < inventoryMaxSize
  }


  def tryEquipWeapon (item:Item) : EquipItemProperty = {
    if(canEquipItem(item)){
      equipWeapon = item
      EquipSuccess
    }else{
      UnknownFail
    }
  }

  private def canEquipItem(item:Item): Boolean = {
    item.weaponEquipable
  }
}
