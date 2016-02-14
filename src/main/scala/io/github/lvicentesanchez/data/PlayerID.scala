package io.github.lvicentesanchez.data

import io.getquill._
import io.getquill.sources.MappedEncoding

case class PlayerID(value: String) extends AnyVal

object PlayerID {

  implicit val decode: MappedEncoding[String, PlayerID] = mappedEncoding[String, PlayerID](PlayerID(_))
  implicit val encode: MappedEncoding[PlayerID, String] = mappedEncoding[PlayerID, String](_.value)
}
