package model

import scalaz._
import Scalaz._

case class Position(x: Int, y: Int)

object Position {

  val xLens = Lens.lensu[Position, Int]((p, _x) => p.copy(x = _x), _.x)
  val yLens = Lens.lensu[Position, Int]((p, _y) => p.copy(y = _y), _.y)

}

