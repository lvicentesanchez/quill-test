package io.github.lvicentesanchez.db

import io.getquill._
import io.getquill.naming.SnakeCase

/**
 * Created by luissanchez on 03/02/2016.
 */
object Cassandra {
  val DB = source(new CassandraSyncSourceConfig[SnakeCase]("DB"))
}
