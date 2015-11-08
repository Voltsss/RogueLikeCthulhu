package model

import scalaz._
import Scalaz._

abstract class Order
case object Up extends Order
case object Down extends Order
case object Right extends Order
case object Left extends Order
case object UpRight extends Order
case object UpLeft extends Order
case object DownRight extends Order
case object DownLeft extends Order
case object NoOrder extends Order

case class Position(col: Int, row: Int) {
  override def equals(other: Any) = other match {
    case that: Position => (that canEqual this) && (this.col == that.col) && (this.row == that.row)
    case _ => false
  }

  def canEqual(other: Any) = other.isInstanceOf[Position]

  def offsetCopy(io : Order = NoOrder) = {
    io match {
      case Up         => Position(this.col, this.row - 1)
      case Down       => Position(this.col, this.row + 1)
      case Right      => Position(this.col + 1, this.row)
      case Left       => Position(this.col - 1, this.row)
      case UpRight    => Position(this.col + 1, this.row - 1)
      case UpLeft     => Position(this.col - 1, this.row - 1)
      case DownRight  => Position(this.col + 1, this.row + 1)
      case DownLeft   => Position(this.col - 1, this.row + 1)
      case _          => Position(this.col, this.row)
    }
  }
}

trait PositionLens {

  val xLens = Lens.lensu[Position, Int]((p, _col) => p.copy(col = _col), _.col)
  val yLens = Lens.lensu[Position, Int]((p, _row) => p.copy(row = _row), _.row)

}
