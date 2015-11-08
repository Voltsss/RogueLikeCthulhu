package roguelike

package object model {

  abstract sealed class CharacterState
  case object Alive extends CharacterState
  case object Dead  extends CharacterState
}
