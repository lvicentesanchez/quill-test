package io.github.lvicentesanchez.db

import io.getquill._
import io.getquill.naming.SnakeCase

object Cassandra {
  val DB = source(new CassandraSyncSourceConfig[SnakeCase]("DB") with NoQueryProbing)
}
