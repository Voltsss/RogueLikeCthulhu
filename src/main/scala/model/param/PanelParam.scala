package model.param

abstract sealed class Panel(val s: String)

object Panel {
  case object Wall         extends Panel("Wall")
  case object Door         extends Panel("Door")
  case object Way          extends Panel("Way")
  case object Floor        extends Panel("Floor")
  case object NothingPanel extends Panel("NothingPanel")

  implicit class PanelParam2String(p: Panel) {
    def appearance = p match {
      case Wall  => "#"
      case Door  => "+"
      case Way   => "*"
      case Floor => "."
      case _     => " "
    }
  }

  implicit class PanelEnterable(p:Panel) {
    def isEnter = p match {
      case Wall   => false
      case Door   => true
      case Way    => true
      case Floor  => true
      case _      => false
    }
  }
}
