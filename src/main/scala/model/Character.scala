package model

trait Charactor {

  final case class Position(var x: Int, var y: Int)
  val position: Position

  def getPosition: (Int, Int) = (position.x, position.y)

  def setPosition(x: Int, y: Int): Unit = {
    position.x = x
    position.y = y
  }

  def setX(x: Int): Unit = setPosition(x, position.y)
  def setY(y: Int): Unit = setPosition(position.x, y)

  def moveRight = setX(position.x + 1)
  def moveLeft  = setX(position.y - 1)
  def moveUp    = setY(position.y - 1)
  def moveDown  = setY(position.y + 1)

}


