package io.github.lvicentesanchez.data

import io.getquill._
import io.getquill.sources.MappedEncoding

case class Attempt(value: Int) extends AnyVal

object Attempt {

  implicit val decode: MappedEncoding[Int, Attempt] = mappedEncoding[Int, Attempt](Attempt(_))
  implicit val encode: MappedEncoding[Attempt, Int] = mappedEncoding[Attempt, Int](_.value)
}
