package io.github.lvicentesanchez.db

import io.getquill._

final class Context[M <: NamingStrategy](prefix: String) extends CassandraSyncContext[M](prefix)

