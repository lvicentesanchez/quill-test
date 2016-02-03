package io.github.lvicentesanchez

import io.getquill._
import io.getquill.naming.SnakeCase
import io.getquill.sources.cassandra.CassandraSyncSource
import io.github.lvicentesanchez.data.{Level, PlayerID}
import io.github.lvicentesanchez.repos.ScoreRepo

/**
 * Created by luissanchez on 30/01/2016.
 */
object Main extends App {

  val sync: CassandraSyncSource[SnakeCase] = source(new CassandraSyncSourceConfig[SnakeCase]("DB"))
  val repo = ScoreRepo(sync)

  repo.playerScoreForLevel(PlayerID("2"), Level("Level1")).foreach(println)

  sync.close()
}
