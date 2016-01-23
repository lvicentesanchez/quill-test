package data

import io.getquill._
import io.getquill.source.MappedEncoding

/**
 * Created by luissanchez on 22/01/2016.
 */
case class PlayerID(value: String) extends AnyVal

object PlayerID {

  implicit val decode: MappedEncoding[String, PlayerID] = mappedEncoding[String, PlayerID](PlayerID(_))
  implicit val encode: MappedEncoding[PlayerID, String] = mappedEncoding[PlayerID, String](_.value)
}
