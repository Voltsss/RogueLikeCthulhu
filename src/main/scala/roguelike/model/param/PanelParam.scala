package roguelike.model.param

abstract sealed class Panel(
  protected val s:  String,
  protected val ap: String,
  protected val en: Boolean
) {
  def appearance: String =
    ap

  def isEnter: Boolean =
    en
}

object Panel {
  case object Wall         extends Panel("Wall", "#", false)
  case object Door         extends Panel("Door", "+", true)
  case object Way          extends Panel("Way", "*", true)
  case object Floor        extends Panel("Floor", ".", true)
  case object NothingPanel extends Panel("NothingPanel", " ", false)
}
