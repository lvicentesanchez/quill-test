package io.github.lvicentesanchez.data

import io.getquill._
import io.getquill.sources.MappedEncoding

case class Points(value: Int) extends AnyVal

object Points {

  implicit val decode: MappedEncoding[Int, Points] = mappedEncoding[Int, Points](Points(_))
  implicit val encode: MappedEncoding[Points, Int] = mappedEncoding[Points, Int](_.value)
}
