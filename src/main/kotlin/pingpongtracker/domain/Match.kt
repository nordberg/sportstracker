package pingpongtracker.domain

data class Match(
    val id: Long,
    val team1: List<Player>,
    val team2: List<Player>,
    val sport: Sport,
    val scoreT1: Short,
    val scoreT2: Short
)