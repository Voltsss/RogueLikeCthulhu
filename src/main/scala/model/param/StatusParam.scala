package model.param

trait StatusParam {
  abstract sealed class Status(s: String)
  case object Active extends Status("Active")
}

object StatusParam