import data._
import db._
import repos.ScoreRepo

object Main extends App {

  val repo = ScoreRepo

  repo.playerScoreForLevel(PlayerID("2"), Level("Level1")).
    run(DB).
    foreach(println)

  DB.close()
}
