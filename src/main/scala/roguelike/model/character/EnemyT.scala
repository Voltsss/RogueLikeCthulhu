package roguelike.model
package character

case class EnemyT(
    name:        String,
    viewChar:    Char,
    experience:  Int,
    hitpoint:    Int,
    attack:      Int,
    defence:     Int,
    agility:     Int,
    dexterity:   Int,
    sanity:      Int,
    state:       CharacterState,
    position:    Position,
    // in Enemy
    enemyKindID: Int
) extends CharacterT

object EnemyT extends EnemyOptics {

  // 初期生成用helper
  def apply(name: String, viewChar: Char): EnemyT =
    EnemyT(
      name,
      viewChar,
      0,                // experience,
      0,                // hitpoint,
      0,                // attack,
      0,                // defence,
      0,                // agility,
      0,                // dexterity,
      0,                // sanity,
      Dead,             // state,
      Position(40, 18), // position,
      0                 // enemyKindID
    )
}

trait EnemyOptics {
  import monocle.{Lens, Prism}
  import monocle.macros.GenLens

  // Prism
  def _enemy: Prism[CharacterT, EnemyT] =
    Prism[CharacterT, EnemyT] {
      case e: EnemyT => Some(e)
      case _         => None
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
  def _enemyKindID = gen(_.enemyKindID)

  private lazy val gen = GenLens[EnemyT]

  private def enemyToCharacter(e: EnemyT): CharacterT = e
}
