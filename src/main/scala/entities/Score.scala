package entities

import data.{ Level, Points, Attempt, PlayerID }

/**
 * Created by luissanchez on 22/01/2016.
 */
case class Score(custId: PlayerID, level: Level, attempt: Attempt, score: Points)
