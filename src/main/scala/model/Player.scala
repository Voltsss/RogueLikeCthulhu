package model

/**
 * Created by volts on 15/04/06.
 */

class Player extends Character{
  var position:Position = Position(40,20)
  val cp = CharacterParameter(
    experience   = 0,
    hitpoint     = 100,
    attack       = 10,
    defence      = 3,
    agility      = 10,
    dexterity    = 10,
    sanity       = 10
  )
  def draw (exScreen: Screen ) : Screen = {
    overwritePositions(Array(model.Position(position.y,position.x)),exScreen,'@')
  }




}
