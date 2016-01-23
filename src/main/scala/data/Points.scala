package data

import io.getquill._
import io.getquill.source.MappedEncoding

/**
 * Created by luissanchez on 22/01/2016.
 */
case class Points(value: Int) extends AnyVal

object Points {

  implicit val decode: MappedEncoding[Int, Points] = mappedEncoding[Int, Points](Points(_))
  implicit val encode: MappedEncoding[Points, Int] = mappedEncoding[Points, Int](_.value)
}
