package io.github.lvicentesanchez.db.entities

import io.github.lvicentesanchez.data.{ Attempt, Level, PlayerID, Points }

final case class Score(custId: PlayerID, level: Level, attempt: Attempt, points: Points)
