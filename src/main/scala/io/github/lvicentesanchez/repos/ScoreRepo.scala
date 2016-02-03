package io.github.lvicentesanchez.repos

import cats.data.ReaderT
import cats.{Eval, Monad}
import io.github.lvicentesanchez.data.{Level, PlayerID}
import io.github.lvicentesanchez.db.Cassandra
import io.github.lvicentesanchez.db.queries.ScoreQueries
import io.github.lvicentesanchez.entities.Score

/**
 * Created by luissanchez on 23/01/2016.
 */
trait ScoreRepo[F[_]] {

  val playerScoreForLevel: Cassandra.DB.type => (PlayerID, Level) => F[List[Score]]

  val playerScoreForLevelK: (PlayerID, Level) => ReaderT[F, Cassandra.DB.type, List[Score]]
}

object ScoreRepo {
  def apply[M[_]](M: Monad[M]): ScoreRepo[M] = new ScoreRepo[M] {

    override val playerScoreForLevel: Cassandra.DB.type => (PlayerID, Level) => M[List[Score]] =
      source => (playerId, level) =>
        M.pureEval(Eval.now(source.run(ScoreQueries.playerScoreForLevel)(playerId, level)))

    override val playerScoreForLevelK: (PlayerID, Level) => ReaderT[M, Cassandra.DB.type, List[Score]] =
      (playerId, level) =>
        ReaderT(playerScoreForLevel(_)(playerId, level))
  }
}
