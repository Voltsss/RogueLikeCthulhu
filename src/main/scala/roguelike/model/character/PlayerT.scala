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
    state:      CharacterState
)
