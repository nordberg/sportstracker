package pingpongtracker.business

import pingpongtracker.domain.Match
import pingpongtracker.domain.Player
import kotlin.math.roundToInt

class Elo {
    companion object {

        data class NewEloPair(val newEloPlayer1: Int, val newEloPlayer2: Int)

        private const val KFACTOR = 32

        fun kFactor(): Int {
            return this.KFACTOR
        }

        fun calculateElo(p1: Player, p2: Player, match: Match): NewEloPair {
            /**
             * Calculates the new Elo rating of two players
             */
            val r1 = Math.pow(10.0, (p1.elo/400).toDouble())
            val r2 = Math.pow(10.0, (p2.elo/400).toDouble())
            val expectedScoreP1 = r1 / (r1 + r2)
            val expectedScoreP2 = r2 / (r1 + r2)

            val newEloP1 = (p1.elo + this.kFactor()*(match.resultOfPlayer1() - expectedScoreP1)).roundToInt()
            val newEloP2 = (p2.elo + this.kFactor()*(match.resultOfPlayer2() - expectedScoreP2)).roundToInt()

            return NewEloPair(newEloP1, newEloP2)
        }
    }
}