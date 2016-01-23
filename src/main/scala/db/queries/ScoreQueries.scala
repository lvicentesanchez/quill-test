package db.queries

import data.{ Level, PlayerID }
import entities.Score
import io.getquill._

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
