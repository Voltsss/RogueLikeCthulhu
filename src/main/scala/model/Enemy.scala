package model

/**
 * Created by volts on 15/05/02.
 */
class Enemy(
             enemyKindID: Int = 0,
             initPosition: Position = Position(40, 18),
             initLevel: Int = 0
             ) extends Character {

  private val enemyChar: Char = EnemyParameterGenerator.getChar(enemyKindID)

  var position:Position = initPosition
  val charaParam = EnemyParameterGenerator.getParam(enemyKindID)

  def draw (exScreen: Screen): Screen =
  {
    overwritePositions(Array(model.Position(position.row, position.col)), exScreen, enemyChar)
  }

}

/** パラメタデータから敵種別IDでパラメタを引くクラス
  * TODO レベルによるステータス補正をかけたパラメタを引けるようにしたい
  */
object EnemyParameterGenerator{
  def getChar(enemyKindID : Int):Char = {
    enemyKindID match {
      case 0 => 'e' //TestID dont use
      case _ => 'E' // Error
    }
  }
  def getParam(enemyKindID : Int):CharacterParameter = {
    enemyKindID match {
      case 0 => CharacterParameter(
        name = "testEnemy",hitpoint = 11, attack =11, defence = 11, agility = 11, dexterity = 11, sanity = 11 //TestID 仮パラメタ
      )
      case _ => CharacterParameter(
        name = "ErrorEnemy",hitpoint = 1, attack =1, defence =1, agility =1, dexterity =1, sanity =1
      )
    }
  }

}
