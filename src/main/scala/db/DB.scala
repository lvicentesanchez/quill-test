package db

import io.getquill.naming.SnakeCase
import io.getquill.source.cassandra.CassandraSyncSource

/**
 * Created by luissanchez on 22/01/2016.
 */
object DB extends CassandraSyncSource[SnakeCase]
