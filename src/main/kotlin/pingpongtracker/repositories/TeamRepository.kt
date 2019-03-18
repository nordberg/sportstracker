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

        fun findTeamsOfSize(size: Int): List<Team> {
            return this.teams.values.filter {
                it.playerIds.size == size
            }
        }

        fun updateTeam(team: Team, newEloTeam: Int) {
            this.teams[team.id] = team.copy(
                elo = newEloTeam
            )
        }

        fun createTeam(playerIds: Set<Long>): Team {
            playerIds.forEach {
                requireNotNull(PlayerRepository.findPlayerById(it))
            }

            val newTeamElo = PlayerRepository.findPlayersById(playerIds.toList())
                .sumBy { it.elo }
                .div(playerIds.size)

            this.teams[counter.incrementAndGet()] = Team(
                id = counter.get(),
                playerIds = playerIds,
                elo = newTeamElo
            )

            return this.teams[counter.get()]!!
        }

        fun getTopTeams(number: Int, teamSize: Int): List<Team> {
            return this.findTeamsOfSize(teamSize)
                .sortedBy { it.elo }
                .asReversed()
                .take(number)
        }
    }
}