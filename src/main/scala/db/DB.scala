package db

import io.getquill.naming.SnakeCase
import io.getquill.source.cassandra.CassandraSyncSource

/**
 * Created by luissanchez on 22/01/2016.
 */
class DB private () extends CassandraSyncSource[SnakeCase]

object DB {

  def apply(): DB = new DB()
}
