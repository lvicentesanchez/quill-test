import data._
import db._
import db.queries.ScoreQueries
import repos.ScoreRepo

object Main extends App {

  val repo: ScoreRepo = ScoreRepo(DB, ScoreQueries)

  repo.playerScoreForLevel(PlayerID("2"), Level("Level1")).foreach(println)

  DB.close()
}
