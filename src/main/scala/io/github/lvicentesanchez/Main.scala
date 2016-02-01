package io.github.lvicentesanchez

import cats._
import cats.data.Kleisli
import cats.std.future._
import cats.std.list._
import io.github.lvicentesanchez.data.{Level, PlayerID}
import io.github.lvicentesanchez.db.DB
import io.github.lvicentesanchez.repos.ScoreRepo

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
/**
 * Created by luissanchez on 30/01/2016.
 */
object Main extends App {

  implicit val M = Monad[Future]
  val F = Functor[Kleisli[Future, DB, ?]] compose Functor[List]
  val db = DB()
  val repo = ScoreRepo(M)

  F.map(repo.playerScoreForLevelK(PlayerID("2"), Level("Level1")))(_.score).
    run(db).
    foreach(println)

  db.close()
}
