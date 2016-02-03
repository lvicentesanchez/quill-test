package io.github.lvicentesanchez

import io.getquill._
import io.getquill.naming.SnakeCase
import io.github.lvicentesanchez.data.PlayerID
import io.github.lvicentesanchez.entities.Score

/**
 * Created by luissanchez on 30/01/2016.
 */
object Main extends App {

  val DB = source(new CassandraSyncSourceConfig[SnakeCase]("DB"))
  val all = quote(query[Score])
  val some = quote {
    (playerId: PlayerID) => query[Score].filter(_.custId == playerId)
  }

  DB.run(all)
  //DB.run(some)(PlayerID("1")).foreach(println)

  DB.close()
}
