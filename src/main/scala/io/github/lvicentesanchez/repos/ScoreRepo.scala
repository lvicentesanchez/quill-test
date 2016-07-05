package io.github.lvicentesanchez.repos

import cats.{ Eval, Monad }
import io.github.lvicentesanchez.data.{ Level, PlayerID, Points }
import io.github.lvicentesanchez.db.entities.Score
import io.github.lvicentesanchez.db.queries.ScoreQueries

trait ScoreRepo[F[_]] {

  val playerScoreForLevel: (PlayerID, Level) => F[List[Score]]

  val updateScoreForLevel: (PlayerID, Level, Points) => F[Unit]
}

object ScoreRepo {

  def apply[M[_]](M: Monad[M], queries: ScoreQueries): ScoreRepo[M] =
    new ScoreRepoImpl(M, queries)
}

private final class ScoreRepoImpl[M[_]] private[repos] (M: Monad[M], queries: ScoreQueries) extends ScoreRepo[M] {

  import queries.db._

  override val playerScoreForLevel: (PlayerID, Level) => M[List[Score]] =
    (playerId, level) =>
      M.pureEval(Eval.now(ctx.run(queries.playerScoreForLevel(playerId, level))))

  override val updateScoreForLevel: (PlayerID, Level, Points) => M[Unit] =
    (playerId, level, points) =>
      M.pureEval(Eval.now(ctx.run(queries.updateScoreForLevel(playerId, level, points))))
}
