package model

import scalaz._
import Scalaz._

import lenses._
import param.Panel

case class Floor(data: Vector[Vector[Panel]])

trait FloorLens {

  val dataLens = Lens.lensu[Floor, Vector[Vector[Panel]]](
    (floor, _data) => floor.copy(data = _data),
    _.data
  )

  def panel(x: Int, y: Int): PLens[Floor, Panel] = {
    dataLens.partial >=> PLens.vectorNthPLens(y) >=> PLens.vectorNthPLens(x)
  }

  def panel(p: Position): PLens[Floor, Panel] = panel(xLens.get(p), yLens.get(p))

}

