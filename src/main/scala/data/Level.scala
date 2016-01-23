package data

import io.getquill._
import io.getquill.source.MappedEncoding

/**
 * Created by luissanchez on 22/01/2016.
 */
case class Level(value: String) extends AnyVal

object Level {

  implicit val decode: MappedEncoding[String, Level] = mappedEncoding[String, Level](Level(_))
  implicit val encode: MappedEncoding[Level, String] = mappedEncoding[Level, String](_.value)
}
