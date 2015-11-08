package roguelike.model

import roguelike.view.Drawable
import roguelike.model

trait Character extends Drawable {

  var position: Position
  val charaParam: CharacterParameter

  def getPositionNum: (Int, Int) = (position.col, position.row)

  def getPosition : Position = position

  def setPosition(col: Int, row: Int): Unit = {
    position = new Position(col,row)
  }
  def setPosition(position: Position): Unit = {
    this.position = position
  }

  def setX(x: Int): Unit = setPosition(x, position.row)
  def setY(y: Int): Unit = setPosition(position.col, y)

  def moveRight     = if(Hit.isEnter(position.offsetCopy(model.Right    ))) position = position.offsetCopy(model.Right)
  def moveLeft      = if(Hit.isEnter(position.offsetCopy(model.Left     ))) position = position.offsetCopy(model.Left)
  def moveUp        = if(Hit.isEnter(position.offsetCopy(model.Up       ))) position = position.offsetCopy(model.Up)
  def moveDown      = if(Hit.isEnter(position.offsetCopy(model.Down     ))) position = position.offsetCopy(model.Down)
  def moveUpRight   = if(Hit.isEnter(position.offsetCopy(model.UpRight  ))) position = position.offsetCopy(model.UpRight)
  def moveUpLeft    = if(Hit.isEnter(position.offsetCopy(model.UpLeft   ))) position = position.offsetCopy(model.UpLeft)
  def moveDownRight = if(Hit.isEnter(position.offsetCopy(model.DownRight))) position = position.offsetCopy(model.DownRight)
  def moveDownLeft  = if(Hit.isEnter(position.offsetCopy(model.DownLeft ))) position = position.offsetCopy(model.DownLeft)

  def setExperience(v: Int) = charaParam.experience = v
  def setHitpoint(v: Int)   = {
    charaParam.hitpoint = v
    updateState
  }
  def setAttack(v: Int)     = charaParam.attack = v
  def setDefence(v: Int)    = charaParam.defence = v
  def setAgility(v: Int)    = charaParam.agility = v
  def setDexterity(v: Int)  = charaParam.dexterity = v
  def setSanity(v: Int)     = charaParam.sanity = v


  def getName = charaParam.name
  def getExperience = charaParam.experience
  def getHitpoint   = charaParam.hitpoint
  def getAttack     = charaParam.attack
  def getDefence    = charaParam.defence
  def getAgility    = charaParam.agility
  def getDexterity  = charaParam.dexterity
  def getSanity     = charaParam.sanity
  def getState      = charaParam.state

  def getLevel = charaParam.experience / 10

  def applyDamage(damage: Int) : CharacterState = {
    setHitpoint(charaParam.hitpoint - damage)
    updateState
    charaParam.state
  }

  def updateState = charaParam.state = if(charaParam.hitpoint <= 0) Dead else Alive
}


case class CharacterParameter(
                               name :           String,
                               viewChar :       Char,
                               var experience:  Int = 0,
                               var hitpoint:    Int = 0,
                               var attack:      Int = 0,
                               var defence:     Int = 0,
                               var agility:     Int = 0,
                               var dexterity:   Int = 0,
                               var sanity:      Int = 0,
                               var state:       CharacterState = Dead
                               )
