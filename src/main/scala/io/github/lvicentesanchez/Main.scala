package io.github.lvicentesanchez

import cats._
import cats.std.future._
import cats.std.list._
import io.getquill._
import io.github.lvicentesanchez.data.{ Level, PlayerID }
import io.github.lvicentesanchez.db.queries.ScoreQueries
import io.github.lvicentesanchez.db.{ Context, CqlDB }
import io.github.lvicentesanchez.repos.ScoreRepo

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Main extends App {

  val M = Monad[Future]
  val F = Functor[Future] compose Functor[List]
  val ctx = new Context[SnakeCase]("DB")
  val db = new CqlDB(ctx)
  val repo = ScoreRepo(M, ScoreQueries(db))

  F.map(repo.playerScoreForLevel(PlayerID("2"), Level("Level1")))(_.points).
    foreach(println)

  db.ctx.close()
}
