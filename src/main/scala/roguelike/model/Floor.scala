package roguelike.model

import scalaz._
import Scalaz._

import roguelike.model.param.Panel

case class Floor(data: Vector[Vector[Panel]]){
  def isEnter(position: Position):Boolean = {
    data(position.row).apply(position.col).isEnter
  }

  def getWidth() : Int = {
    data(0).size
  }

  def getHeight() : Int = {
    data.size
  }

  def getPanel(width : Int, height : Int) : Panel = {
    data(height).apply(width)
  }
}
