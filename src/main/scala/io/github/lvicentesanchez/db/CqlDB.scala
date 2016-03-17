package io.github.lvicentesanchez.db

import io.getquill._
import io.getquill.naming.{ NamingStrategy, SnakeCase }
import io.getquill.sources.cassandra.CassandraSyncSource

trait CqlDB[M <: NamingStrategy] {
  def db: CassandraSyncSource[M]
}

object CqlDB extends CqlDB[SnakeCase] {
  override val db: CassandraSyncSource[SnakeCase] = source(new CassandraSyncSourceConfig[SnakeCase]("DB"))
}
