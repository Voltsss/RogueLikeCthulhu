package roguelike.model
package character

trait CharacterT {

  // need parameters
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
}
