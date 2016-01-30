package io.github.lvicentesanchez.entities

import io.github.lvicentesanchez.data.{Attempt, Level, PlayerID, Points}

/**
 * Created by luissanchez on 22/01/2016.
 */
case class Score(custId: PlayerID, level: Level, attempt: Attempt, score: Points)
