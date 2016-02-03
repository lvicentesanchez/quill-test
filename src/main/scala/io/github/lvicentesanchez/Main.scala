package io.github.lvicentesanchez

import io.getquill._
import io.getquill.naming.SnakeCase
import io.github.lvicentesanchez.entities.Score

/**
 * Created by luissanchez on 30/01/2016.
 */
object Main extends App {

  val DB = source(new CassandraSyncSourceConfig[SnakeCase]("DB"))

  DB.run(query[Score]).foreach(println)

  DB.close()
}
