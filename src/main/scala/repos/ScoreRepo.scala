package repos

import data.{ Level, PlayerID }
import db.DB
import db.queries.ScoreQueries
import entities.Score

/**
 * Created by luissanchez on 23/01/2016.
 */
trait ScoreRepo {

  def playerScoreForLevel: (PlayerID, Level) => List[Score]
}

object ScoreRepo {

  def apply(db: DB.type, queries: ScoreQueries.type): ScoreRepo =
    new ScoreRepoImpl(db, queries)
}

private final class ScoreRepoImpl(db: DB.type, queries: ScoreQueries.type) extends ScoreRepo {
  override val playerScoreForLevel: (PlayerID, Level) => List[Score] =
    db.run(queries.playerScoreForLevel)
}
