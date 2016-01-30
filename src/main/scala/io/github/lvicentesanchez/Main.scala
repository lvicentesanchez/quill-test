package io.github.lvicentesanchez

import cats._
import cats.std.future._
import cats.std.list._
import io.github.lvicentesanchez.data.{ Level, PlayerID }
import io.github.lvicentesanchez.db.DB
import io.github.lvicentesanchez.repos.ScoreRepo

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
/**
 * Created by luissanchez on 30/01/2016.
 */
object Main extends App {

  val M = Monad[Future]
  val F = M compose Functor[List]
  val db = DB()
  val repo = ScoreRepo(M)

  val scores =
    repo.playerScoreForLevel(PlayerID("2"), Level("Level1")).
      run(db)

  F.map(scores)(_.score).foreach(println)

  db.close()
}
