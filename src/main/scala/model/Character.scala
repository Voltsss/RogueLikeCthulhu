package model

import view.Drawable

trait Character extends Drawable {

  var position: Position

  def getPositionNum: (Int, Int) = (position.col, position.row)

  def getPostion : Position = position

  def setPosition(col: Int, row: Int): Unit = {
    position = new Position(col,row)
  }
  def setPosition(position: Position): Unit = {
    this.position = position
  }

  def setX(x: Int): Unit = setPosition(x, position.row)
  def setY(y: Int): Unit = setPosition(position.col, y)

  def moveRight = if(Hit.isEnter(Position(position.col + 1, position.row))) position = new Position(position.col + 1, position.row)
  def moveLeft  = if(Hit.isEnter(Position(position.col - 1, position.row))) position = new Position(position.col - 1, position.row)
  def moveUp    = if(Hit.isEnter(Position(position.col, position.row - 1))) position = new Position(position.col, position.row - 1)
  def moveDown  = if(Hit.isEnter(Position(position.col, position.row + 1))) position = new Position(position.col, position.row + 1)
  def moveUpRight = if(Hit.isEnter(Position(position.col + 1, position.row - 1))) position = new Position(position.col + 1, position.row - 1)
  def moveUpLeft = if(Hit.isEnter(Position(position.col - 1, position.row - 1))) position = new Position(position.col - 1, position.row - 1)
  def moveDownRight = if(Hit.isEnter(Position(position.col + 1, position.row + 1))) position = new Position(position.col + 1, position.row + 1)
  def moveDownLeft = if(Hit.isEnter(Position(position.col - 1, position.row + 1))) position = new Position(position.col - 1, position.row + 1)


  val charaParam: CharacterParameter

  def setExperience(v: Int) = charaParam.experience = v
  def setHitpoint(v: Int)   = charaParam.hitpoint = v
  def setAttack(v: Int)     = charaParam.attack = v
  def setDefence(v: Int)    = charaParam.defence = v
  def setAgility(v: Int)    = charaParam.agility = v
  def setDexterity(v: Int)  = charaParam.dexterity = v
  def setSanity(v: Int)     = charaParam.sanity = v

  def getExperience = charaParam.experience
  def getHitpoint   = charaParam.hitpoint
  def getAttack     = charaParam.attack
  def getDefence    = charaParam.defence
  def getAgility    = charaParam.agility
  def getDexterity  = charaParam.dexterity
  def getSanity     = charaParam.sanity

  def getLevel = charaParam.experience / 10
}


case class CharacterParameter(
                               var experience: Int = 0,
                               var hitpoint:   Int = 0,
                               var attack:     Int = 0,
                               var defence:    Int = 0,
                               var agility:    Int = 0,
                               var dexterity:  Int = 0,
                               var sanity:     Int = 0
                               )