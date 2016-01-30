package io.github.lvicentesanchez.repos

import cats.data.ReaderT
import cats.{ Eval, Monad }
import io.github.lvicentesanchez.data.{ Level, PlayerID }
import io.github.lvicentesanchez.db.DB
import io.github.lvicentesanchez.db.queries.ScoreQueries
import io.github.lvicentesanchez.entities.Score

/**
 * Created by luissanchez on 23/01/2016.
 */
trait ScoreRepo[F[_]] {

  def playerScoreForLevel(playerId: PlayerID, level: Level): ReaderT[F, DB, List[Score]]
}

object ScoreRepo {
  def apply[M[_]](M: Monad[M]): ScoreRepo[M] = new ScoreRepo[M] {

    override def playerScoreForLevel(playerId: PlayerID, level: Level): ReaderT[M, DB, List[Score]] =
      ReaderT(DB => M.pureEval(Eval.now(DB.run(ScoreQueries.playerScoreForLevel)(playerId, level))))
  }
}
