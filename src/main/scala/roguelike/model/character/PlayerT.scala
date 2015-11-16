package roguelike.model
package character

case class PlayerT(
    name :      String,
    viewChar:   Char,
    experience: Int,
    hitpoint:   Int,
    attack:     Int,
    defence:    Int,
    agility:    Int,
    dexterity:  Int,
    sanity:     Int,
    state:      CharacterState,
    position:   Position
) extends CharacterT

object PlayerT extends PlayerOptics {

  // 初期生成用helper
  def apply(): PlayerT =
    PlayerT(
      "Player",         // name
      '@',              // viewchar
      0,                // experience,
      100,              // hitpoint,
      20,               // attack,
      3,                // defence,
      10,               // agility,
      10,               // dexterity,
      10,               // sanity,
      Alive,             // state,
      Position(40, 20)  // position,
    )
}

trait PlayerOptics {
  import monocle.{Lens, Prism}
  import monocle.macros.GenLens

  // Prism
  def _player: Prism[CharacterT, PlayerT] =
    Prism[CharacterT, PlayerT] {
      case p: PlayerT => Some(p)
      case _          => None
    }(enemyToCharacter)

  // Lenses
  def _name        = gen(_.name)
  def _viewChar    = gen(_.viewChar)
  def _experience  = gen(_.experience)
  def _hitpoint    = gen(_.hitpoint)
  def _attack      = gen(_.attack)
  def _defence     = gen(_.defence)
  def _agility     = gen(_.agility)
  def _dexterity   = gen(_.dexterity)
  def _sanity      = gen(_.sanity)
  def _state       = gen(_.state)
  def _position    = gen(_.position)

  private lazy val gen = GenLens[PlayerT]

  private def enemyToCharacter(p: PlayerT): CharacterT = p
}
