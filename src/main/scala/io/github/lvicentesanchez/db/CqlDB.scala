package io.github.lvicentesanchez.db

import io.getquill._
import io.github.lvicentesanchez.data.{ Attempt, Level, PlayerID, Points }

final class CqlDB(val ctx: Context[SnakeCase]) {

  import ctx._

  implicit val decodeAttempt: MappedEncoding[Int, Attempt] = mappedEncoding[Int, Attempt](Attempt(_))
  implicit val encodeAttempt: MappedEncoding[Attempt, Int] = mappedEncoding[Attempt, Int](_.value)

  implicit val decodeLevel: MappedEncoding[String, Level] = mappedEncoding[String, Level](Level(_))
  implicit val encodeLevel: MappedEncoding[Level, String] = mappedEncoding[Level, String](_.value)

  implicit val decodePlayerID: MappedEncoding[String, PlayerID] = mappedEncoding[String, PlayerID](PlayerID(_))
  implicit val encodePlayerID: MappedEncoding[PlayerID, String] = mappedEncoding[PlayerID, String](_.value)

  implicit val decodePoints: MappedEncoding[Int, Points] = mappedEncoding[Int, Points](Points(_))
  implicit val encodePoints: MappedEncoding[Points, Int] = mappedEncoding[Points, Int](_.value)
}
