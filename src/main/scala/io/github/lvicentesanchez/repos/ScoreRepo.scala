package io.github.lvicentesanchez.repos

import cats.data.ReaderT
import cats.{ Eval, Monad }
import io.getquill.naming.SnakeCase
import io.github.lvicentesanchez.data.{ Level, PlayerID, Points }
import io.github.lvicentesanchez.db.CqlDB
import io.github.lvicentesanchez.db.entities.Score
import io.github.lvicentesanchez.db.queries.ScoreQueries

trait ScoreRepo[F[_]] {

  val playerScoreForLevel: CqlDB[SnakeCase] => (PlayerID, Level) => F[List[Score]]

  val playerScoreForLevelK: (PlayerID, Level) => ReaderT[F, CqlDB[SnakeCase], List[Score]]

  val updateScoreForLevel: CqlDB[SnakeCase] => (PlayerID, Level, Points) => F[Unit]

  val updateScoreForLevelK: (PlayerID, Level, Points) => ReaderT[F, CqlDB[SnakeCase], Unit]
}

object ScoreRepo {

  def apply[M[_]](M: Monad[M]): ScoreRepo[M] = new ScoreRepoImpl(M)
}

private final class ScoreRepoImpl[M[_]](M: Monad[M]) extends ScoreRepo[M] {

  override val playerScoreForLevel: CqlDB[SnakeCase] => (PlayerID, Level) => M[List[Score]] =
    source => (playerId, level) =>
      M.pureEval(Eval.now(source.db.run(ScoreQueries.playerScoreForLevel)(playerId, level)))

  override val playerScoreForLevelK: (PlayerID, Level) => ReaderT[M, CqlDB[SnakeCase], List[Score]] =
    (playerId, level) =>
      ReaderT(playerScoreForLevel(_)(playerId, level))

  override val updateScoreForLevel: CqlDB[SnakeCase] => (PlayerID, Level, Points) => M[Unit] =
    source => (playerId, level, points) =>
      M.pureEval(Eval.now(source.db.run(ScoreQueries.updateScoreForLevel)((playerId, level, points))))

  override val updateScoreForLevelK: (PlayerID, Level, Points) => ReaderT[M, CqlDB[SnakeCase], Unit] =
    (playerId, level, points) =>
      ReaderT(updateScoreForLevel(_)(playerId, level, points))
}
