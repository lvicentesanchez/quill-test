package io.github.lvicentesanchez

import cats._
import cats.data.Kleisli
import cats.std.future._
import cats.std.list._
import io.github.lvicentesanchez.data.{ Level, PlayerID }
import io.github.lvicentesanchez.db.Cassandra
import io.github.lvicentesanchez.repos.ScoreRepo

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Main extends App {

  implicit val M = Monad[Future]
  val F = Functor[Kleisli[Future, Cassandra.DB.type, ?]] compose Functor[List]
  val repo = ScoreRepo(M)

  F.map(repo.playerScoreForLevelK(PlayerID("2"), Level("Level1")))(_.score).
    run(Cassandra.DB).
    foreach(println)

  Cassandra.DB.close()
}
