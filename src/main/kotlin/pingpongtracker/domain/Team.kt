package pingpongtracker.domain

data class Team(
    val id: Long,
    val playerIds: Set<Long>,
    val elo: Int
)
