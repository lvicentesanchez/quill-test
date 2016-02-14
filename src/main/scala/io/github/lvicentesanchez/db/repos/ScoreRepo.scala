package io.github.lvicentesanchez.db.repos

import cats.data.ReaderT
import cats.{ Eval, Monad }
import io.github.lvicentesanchez.data.{ Level, PlayerID }
import io.github.lvicentesanchez.db.Cassandra._
import io.github.lvicentesanchez.db.entities.Score
import io.github.lvicentesanchez.db.queries.ScoreQueries

trait ScoreRepo[F[_]] {

  val playerScoreForLevel: DB.type => (PlayerID, Level) => F[List[Score]]

  val playerScoreForLevelK: (PlayerID, Level) => ReaderT[F, DB.type, List[Score]]
}

object ScoreRepo {

  def apply[M[_]](M: Monad[M]): ScoreRepo[M] = new ScoreRepoImpl(M)
}

private final class ScoreRepoImpl[M[_]](M: Monad[M]) extends ScoreRepo[M] {

  override val playerScoreForLevel: DB.type => (PlayerID, Level) => M[List[Score]] =
    source => (playerId, level) =>
      M.pureEval(Eval.now(source.run(ScoreQueries.playerScoreForLevel)(playerId, level)))

  override val playerScoreForLevelK: (PlayerID, Level) => ReaderT[M, DB.type, List[Score]] =
    (playerId, level) =>
      ReaderT(playerScoreForLevel(_)(playerId, level))
}
