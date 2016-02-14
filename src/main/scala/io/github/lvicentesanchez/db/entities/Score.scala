package io.github.lvicentesanchez.db.entities

import io.github.lvicentesanchez.data.{ Attempt, Level, PlayerID, Points }

case class Score(custId: PlayerID, level: Level, attempt: Attempt, score: Points)
