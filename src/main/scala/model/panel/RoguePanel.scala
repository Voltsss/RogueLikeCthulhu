package model.panel

// Panel types
abstract sealed trait PanelType
object Wall extends PanelType { override def toString = "Wall" }
object Door extends PanelType { override def toString = "Door" }
object Way extends PanelType { override def toString = "Way" }
object Floor extends PanelType { override def toString = "Floor" }
object NothingPanel extends PanelType { override def toString = "NothingPanel" }

case class RoguePanel(panelType: PanelType, enterPerm: Boolean = false) {
  def parse = panelType match {
    case Wall => "#"
    case Door => "+"
    case Way => "*"
    case Floor => "."
    case _ => " "
  }
}