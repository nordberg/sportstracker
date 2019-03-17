package pingpongtracker.domain

import java.time.LocalDate

data class Match(
    val id: Long,
    val team1: Long,
    val team2: Long,
    val sport: Sport,
    val scoreT1: Short,
    val scoreT2: Short,
    val date: LocalDate
) {

    fun resultOfTeam1(): Double {
        return when {
            this.scoreT1 > this.scoreT2 -> 1.0
            this.scoreT1 == this.scoreT2 -> 0.5
            else -> 0.0
        }
    }

    fun resultOfTeam2(): Double {
        return when {
            this.scoreT2 > this.scoreT1 -> 1.0
            this.scoreT2 == this.scoreT1 -> 0.5
            else -> 0.0
        }
    }
}
