package io.github.lvicentesanchez.db

import io.getquill._
import io.github.lvicentesanchez.data.{ Attempt, Level, PlayerID, Points }

final class CqlDB(val ctx: Context[SnakeCase]) {

  import ctx._

  implicit val decodeAttempt: MappedEncoding[Int, Attempt] = MappedEncoding[Int, Attempt](Attempt(_))
  implicit val encodeAttempt: MappedEncoding[Attempt, Int] = MappedEncoding[Attempt, Int](_.value)

  implicit val decodeLevel: MappedEncoding[String, Level] = MappedEncoding[String, Level](Level(_))
  implicit val encodeLevel: MappedEncoding[Level, String] = MappedEncoding[Level, String](_.value)

  implicit val decodePlayerID: MappedEncoding[String, PlayerID] = MappedEncoding[String, PlayerID](PlayerID(_))
  implicit val encodePlayerID: MappedEncoding[PlayerID, String] = MappedEncoding[PlayerID, String](_.value)

  implicit val decodePoints: MappedEncoding[Int, Points] = MappedEncoding[Int, Points](Points(_))
  implicit val encodePoints: MappedEncoding[Points, Int] = MappedEncoding[Points, Int](_.value)
}
