package io.github.lvicentesanchez.db.queries

import io.getquill._
import io.github.lvicentesanchez.data.{Level, PlayerID}
import io.github.lvicentesanchez.entities.Score

/**
 * Created by luissanchez on 23/01/2016.
 */
object ScoreQueries {

  val playerScoreForLevel = quote {
    (playerId: PlayerID, level: Level) =>
      query[Score].filter(
        score => score.custId == playerId && score.level == level
      )
  }
}
