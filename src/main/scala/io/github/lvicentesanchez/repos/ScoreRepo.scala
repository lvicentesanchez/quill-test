package io.github.lvicentesanchez.repos

import io.github.lvicentesanchez.data.{Level, PlayerID}
import io.github.lvicentesanchez.db.Cassandra
import io.github.lvicentesanchez.db.queries.ScoreQueries
import io.github.lvicentesanchez.entities.Score

/**
 * Created by luissanchez on 23/01/2016.
 */
trait ScoreRepo {

  def playerScoreForLevel(playerId: PlayerID, level: Level): List[Score]
}

object ScoreRepo {
  def apply(source: Cassandra.DB.type): ScoreRepo = new ScoreRepoImpl(source)
}

private class ScoreRepoImpl(source: Cassandra.DB.type) extends ScoreRepo {

  override def playerScoreForLevel(playerId: PlayerID, level: Level): List[Score] =
    source.run(ScoreQueries.playerScoreForLevel)(playerId, level)
}
