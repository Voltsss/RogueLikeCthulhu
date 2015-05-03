package model

import scalaz._
import Scalaz._

case class Position(col: Int, row: Int) {
  override def equals(other: Any) = other match {
    case that: Position => (that canEqual this) && (this.col == that.col) && (this.row == that.row)
    case _ => false
  }

  def canEqual(other: Any) = other.isInstanceOf[Position]
}

trait PositionLens {

  val xLens = Lens.lensu[Position, Int]((p, _col) => p.copy(col = _col), _.col)
  val yLens = Lens.lensu[Position, Int]((p, _row) => p.copy(row = _row), _.row)

}

