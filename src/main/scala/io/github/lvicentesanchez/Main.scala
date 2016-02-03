package io.github.lvicentesanchez

import io.github.lvicentesanchez.data.{Level, PlayerID}
import io.github.lvicentesanchez.db.Cassandra
import io.github.lvicentesanchez.repos.ScoreRepo

/**
 * Created by luissanchez on 30/01/2016.
 */
object Main extends App {

  val repo = ScoreRepo(Cassandra.DB)

  repo.playerScoreForLevel(PlayerID("2"), Level("Level1")).foreach(println)

  Cassandra.DB.close()
}
