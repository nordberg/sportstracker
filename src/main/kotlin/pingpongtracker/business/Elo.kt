package pingpongtracker.business

import pingpongtracker.domain.Match
import pingpongtracker.domain.Team
import kotlin.math.roundToInt

class Elo {
    companion object {

        private const val KFACTOR = 32

        fun kFactor(): Int {
            return this.KFACTOR
        }

        fun calculateElo(t1: Team, t2: Team, match: Match): NewEloPair {
            /**
             * Calculates the new Elo rating of two teams
             */
            val r1 = Math.pow(10.0, (t1.elo / 400).toDouble())
            val r2 = Math.pow(10.0, (t2.elo / 400).toDouble())
            val expectedScoreT1 = r1 / (r1 + r2)
            val expectedScoreT2 = r2 / (r1 + r2)

            val newEloT1 = (t1.elo + this.kFactor() * (match.resultOfTeam1() - expectedScoreT1)).roundToInt()
            val newEloT2 = (t2.elo + this.kFactor() * (match.resultOfTeam2() - expectedScoreT2)).roundToInt()

            return NewEloPair(newEloT1, newEloT2)
        }
    }

    data class NewEloPair(val newEloTeam1: Int, val newEloTeam2: Int)
}