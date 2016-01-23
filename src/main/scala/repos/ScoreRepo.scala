package repos

import cats.data.Reader
import data.{ Level, PlayerID }
import db.DB
import db.queries.ScoreQueries
import entities.Score

/**
 * Created by luissanchez on 23/01/2016.
 */
trait ScoreRepo {

  def playerScoreForLevel(playerId: PlayerID, level: Level): Reader[DB.type, List[Score]]
}

object ScoreRepo extends ScoreRepo {

  override def playerScoreForLevel(playerId: PlayerID, level: Level): Reader[DB.type, List[Score]] =
    Reader(DB => DB.run(ScoreQueries.playerScoreForLevel)(playerId, level))
}
