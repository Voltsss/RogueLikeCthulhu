package roguelike.model
package character

trait CharacterT { self =>

  // parameters
  def name:       String
  def viewChar:   Char
  def experience: Int
  def hitpoint:   Int
  def attack:     Int
  def defence:    Int
  def agility:    Int
  def dexterity:  Int
  def sanity:     Int
  def state:      CharacterState
  def position:   Position


  //
  // status確認系処理
  //

  // デフォルトはstateをチェックする
  def isAlive: Boolean =
    state == Alive

  // デフォルトはstateをチェックする
  def isDead: Boolean =
    state == Dead

  // デフォルトは経験値を10で割った値とする
  def level: Int =
    experience / 10
}
