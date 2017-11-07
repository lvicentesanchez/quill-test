package io.github.lvicentesanchez.data

final case class Attempt(value: Int) extends AnyVal

object Attempt {

  implicit val ordering: Ordering[Attempt] = Ordering[Int].on(_.value)
}
