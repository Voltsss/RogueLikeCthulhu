package model.panel

// Panel types
abstract sealed trait PanelType
case object Wall  extends PanelType
case object Door  extends PanelType
case object Way   extends PanelType
case object Floor extends PanelType
case object NothingPanel extends PanelType

object PanelTypeImplicits {
  implicit class PanelType2String(p: PanelType) {
    def appearance = p match {
      case Wall  => "#"
      case Door  => "+"
      case Way   => "*"
      case Floor => "."
      case _     => " "
    }
  }
}
