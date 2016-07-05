package io.github.lvicentesanchez.data

case class Attempt(value: Int) extends AnyVal

object Attempt {

  implicit val ordering: Ordering[Attempt] = Ordering[Int].on(_.value)
}
