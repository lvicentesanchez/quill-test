package io.github.lvicentesanchez.db.queries

import io.getquill._
import io.github.lvicentesanchez.data.{ Level, PlayerID }
import io.github.lvicentesanchez.db.entities.Score

object ScoreQueries {

  val playerScoreForLevel = quote {
    (playerId: PlayerID, level: Level) =>
      query[Score].filter(
        score => score.custId == playerId && score.level == level
      )
  }
}
