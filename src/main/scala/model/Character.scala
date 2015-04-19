package model

import view.Drawable

trait Character extends Drawable {

  var position: Position

  def getPosition: (Int, Int) = (position.x, position.y)

  def setPosition(x: Int, y: Int): Unit = {
    position = new Position(x,y)
  }

  def setX(x: Int): Unit = setPosition(x, position.y)
  def setY(y: Int): Unit = setPosition(position.x, y)

  def moveRight = if(Hit.isEnter(Position(position.x + 1, position.y))) position = new Position(position.x + 1, position.y)
  def moveLeft  = if(Hit.isEnter(Position(position.x - 1, position.y))) position = new Position(position.x - 1, position.y)
  def moveUp    = if(Hit.isEnter(Position(position.x, position.y - 1))) position = new Position(position.x, position.y - 1)
  def moveDown  = if(Hit.isEnter(Position(position.x, position.y + 1))) position = new Position(position.x, position.y + 1)
  def moveUpRight = if(Hit.isEnter(Position(position.x + 1, position.y - 1))) position = new Position(position.x + 1, position.y - 1)
  def moveUpLeft = if(Hit.isEnter(Position(position.x - 1, position.y - 1))) position = new Position(position.x - 1, position.y - 1)
  def moveDownRight = if(Hit.isEnter(Position(position.x + 1, position.y + 1))) position = new Position(position.x + 1, position.y + 1)
  def moveDownLeft = if(Hit.isEnter(Position(position.x - 1, position.y + 1))) position = new Position(position.x - 1, position.y + 1)

  final case class CharacterParameter(
    var experience: Int = 0,
    var hitpoint:   Int = 0,
    var attack:     Int = 0,
    var defence:    Int = 0,
    var agility:    Int = 0,
    var dexterity:  Int = 0,
    var sanity:     Int = 0
  )
  val cp: CharacterParameter

  def setExperience(v: Int) = cp.experience = v
  def setHitpoint(v: Int)   = cp.hitpoint = v
  def setAttack(v: Int)     = cp.attack = v
  def setDefence(v: Int)    = cp.defence = v
  def setAgility(v: Int)    = cp.agility = v
  def setDexterity(v: Int)  = cp.dexterity = v
  def setSanity(v: Int)     = cp.sanity = v

  def getExperience = cp.experience
  def getHitpoint   = cp.hitpoint
  def getAttack     = cp.attack
  def getDefence    = cp.defence
  def getAgility    = cp.agility
  def getDexterity  = cp.dexterity
  def getSanity     = cp.sanity

  def getLevel = cp.experience / 10
}

