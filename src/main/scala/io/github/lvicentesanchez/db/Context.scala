package io.github.lvicentesanchez.db

import io.getquill._

final class Context[M <: NamingStrategy](naming: M, prefix: String) extends CassandraSyncContext(naming, prefix)

