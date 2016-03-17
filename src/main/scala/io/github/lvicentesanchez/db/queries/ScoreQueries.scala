package io.github.lvicentesanchez.db.queries

import io.getquill._
import io.getquill.sources.cassandra.ops._
import io.github.lvicentesanchez.data.{ Level, PlayerID, Points }
import io.github.lvicentesanchez.db.entities.Score

object ScoreQueries {

  val playerScoreForLevel = quote {
    (playerId: PlayerID, level: Level) =>
      query[Score].filter(
        score => score.custId == playerId && score.level == level
      )
  }

  val updateScoreForLevel = quote {
    (playerId: PlayerID, level: Level, points: Points) =>
      query[Score].update(_.points -> points).ifCond(
        score => score.custId == playerId && score.level == level
      )
  }
}
