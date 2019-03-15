package pingpongtracker.repositories

import pingpongtracker.domain.Team
import java.util.concurrent.atomic.AtomicLong

class TeamRepository {

    companion object {

        private val counter = AtomicLong()

        private val teams = mutableMapOf(
            counter.incrementAndGet() to Team(
                id = counter.get(),
                playerIds = setOf(1),
                elo = (2000..2500).random()
            ),
            counter.incrementAndGet() to Team(
                id = counter.get(),
                playerIds = setOf(2),
                elo = (2000..2500).random()
            ),
            counter.incrementAndGet() to Team(
                id = counter.get(),
                playerIds = setOf(3),
                elo = (2000..2500).random()
            ),
            counter.incrementAndGet() to Team(
                id = counter.get(),
                playerIds = setOf(4),
                elo = (2000..2500).random()
            ),
            counter.incrementAndGet() to Team(
                id = counter.get(),
                playerIds = setOf(1, 2),
                elo = (2000..2500).random()
            ),
            counter.incrementAndGet() to Team(
                id = counter.get(),
                playerIds = setOf(3, 4),
                elo = (2000..2500).random()
            )
        )

        fun teams(): List<Team> {
            return this.teams.values.toList()
        }

        fun findTeamById(id: Long): Team? {
            return this.teams[id]
        }

        fun findTeamContainingExactlyPlayersWithIds(ids: List<Long>): Team? {
            return this.teams.values.firstOrNull {
                it.playerIds == setOf(ids)
            }
        }

        fun findTeamContainingPlayerWithId(id: Long): List<Team> {
            return this.teams.values.filter {
                id in it.playerIds
            }
        }

        fun updateTeam(team: Team, newEloTeam: Int) {
            this.teams[team.id] = team.copy(
                elo = newEloTeam
            )
        }
    }
}