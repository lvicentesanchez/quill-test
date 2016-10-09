package io.github.lvicentesanchez.repos

import cats.MonadError
import io.github.lvicentesanchez.data.{ Level, PlayerID, Points }
import io.github.lvicentesanchez.db.entities.Score
import io.github.lvicentesanchez.db.queries.ScoreQueries

trait ScoreRepo[F[_]] {

  val playerScoreForLevel: (PlayerID, Level) => F[List[Score]]

  val updateScoreForLevel: (PlayerID, Level, Points) => F[Unit]
}

object ScoreRepo {

  def apply[M[_]](M: MonadError[M, Throwable], queries: ScoreQueries): ScoreRepo[M] =
    new ScoreRepoImpl(M, queries)
}

private final class ScoreRepoImpl[M[_]] private[repos] (M: MonadError[M, Throwable], queries: ScoreQueries) extends ScoreRepo[M] {

  import queries.db._
  import queries.db.ctx._

  override val playerScoreForLevel: (PlayerID, Level) => M[List[Score]] =
    (playerId, level) =>
      M.catchNonFatal(ctx.run(queries.playerScoreForLevel(playerId, level)))

  override val updateScoreForLevel: (PlayerID, Level, Points) => M[Unit] =
    (playerId, level, points) =>
      M.catchNonFatal(ctx.run(queries.updateScoreForLevel(playerId, level, points)))
}
