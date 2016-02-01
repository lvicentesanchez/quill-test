package io.github.lvicentesanchez.repos

import cats.data.ReaderT
import cats.{Eval, Monad}
import io.github.lvicentesanchez.data.{Level, PlayerID}
import io.github.lvicentesanchez.db.DB
import io.github.lvicentesanchez.db.queries.ScoreQueries
import io.github.lvicentesanchez.entities.Score

/**
 * Created by luissanchez on 23/01/2016.
 */
trait ScoreRepo[F[_]] {

  def playerScoreForLevel: DB => (PlayerID, Level) => F[List[Score]]

  def playerScoreForLevelK: (PlayerID, Level) => ReaderT[F, DB, List[Score]]
}

object ScoreRepo {
  def apply[M[_]](M: Monad[M]): ScoreRepo[M] = new ScoreRepo[M] {

    override val playerScoreForLevel: DB => (PlayerID, Level) => M[List[Score]] =
      db => (playerId, level) =>
        M.pureEval(Eval.now(db.run(ScoreQueries.playerScoreForLevel)(playerId, level)))

    override val playerScoreForLevelK: (PlayerID, Level) => ReaderT[M, DB, List[Score]] =
      (playerId, level) =>
        ReaderT(playerScoreForLevel(_)(playerId, level))
  }
}
