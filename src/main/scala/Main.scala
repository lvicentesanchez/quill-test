import cats.std.future._
import cats.std.list._
import cats.{ Functor, Monad }
import data._
import db._
import repos.ScoreRepo

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

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
