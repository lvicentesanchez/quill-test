package io.github.lvicentesanchez.db.queries

import io.github.lvicentesanchez.data.{ Level, PlayerID, Points }
import io.github.lvicentesanchez.db.CqlDB
import io.github.lvicentesanchez.db.entities.Score

final class ScoreQueries private (val db: CqlDB) {

  import db.ctx._

  def playerScoreForLevel(playerId: PlayerID, level: Level) =
    quote {
      query[Score].filter(
        score => score.custId == lift(playerId) && score.level == lift(level)
      )
    }

  def updateScoreForLevel(playerId: PlayerID, level: Level, points: Points) =
    quote {
      query[Score].update(_.points -> lift(points)).ifCond(
        score => score.custId == lift(playerId) && score.level == lift(level)
      )
    }
}

object ScoreQueries {

  def apply(db: CqlDB): ScoreQueries = new ScoreQueries(db)
}
