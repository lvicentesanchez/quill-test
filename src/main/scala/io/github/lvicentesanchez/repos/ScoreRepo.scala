package io.github.lvicentesanchez.repos

import io.getquill.naming.SnakeCase
import io.getquill.sources.cassandra.CassandraSyncSource
import io.github.lvicentesanchez.data.{Level, PlayerID}
import io.github.lvicentesanchez.db.queries.ScoreQueries
import io.github.lvicentesanchez.entities.Score

/**
 * Created by luissanchez on 23/01/2016.
 */
trait ScoreRepo {

  def playerScoreForLevel(playerId: PlayerID, level: Level): List[Score]
}

object ScoreRepo {
  def apply(source: CassandraSyncSource[SnakeCase]): ScoreRepo = new ScoreRepoImpl(source)
}

private class ScoreRepoImpl(source: CassandraSyncSource[SnakeCase]) extends ScoreRepo {

  override def playerScoreForLevel(playerId: PlayerID, level: Level): List[Score] =
    source.run(ScoreQueries.playerScoreForLevel)(playerId, level)
}
