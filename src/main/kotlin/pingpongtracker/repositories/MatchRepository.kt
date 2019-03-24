package pingpongtracker.repositories

import pingpongtracker.business.Elo
import pingpongtracker.domain.Match
import pingpongtracker.domain.Sport
import java.time.LocalDate
import java.util.concurrent.atomic.AtomicLong

class MatchRepository {
    companion object {
        private val counter = AtomicLong()

        private val matches = mutableMapOf(
            counter.incrementAndGet() to Match(
                id = counter.get(),
                team1 = 1L,
                team2 = 2L,
                scoreT1 = 1,
                scoreT2 = 2,
                sport = Sport.EIGHTBALL,
                date = LocalDate.of(2018, 1, 1)

            ),
            counter.incrementAndGet() to Match(
                id = counter.get(),
                team1 = 2L,
                team2 = 3L,
                scoreT1 = 0,
                scoreT2 = 2,
                sport = Sport.PINGPONG,
                date = LocalDate.of(2018, 1, 1)
            ),
            counter.incrementAndGet() to Match(
                id = counter.get(),
                team1 = 2L,
                team2 = 4L,
                scoreT1 = 2,
                scoreT2 = 1,
                sport = Sport.PINGPONG,
                date = LocalDate.of(2018, 1, 1)
            )
        )

        fun matchesByPlayerId(playerId: Long): List<Match> {
            val teamIds = TeamRepository.findTeamContainingPlayerWithId(playerId).map { it.id }
            return matches.values.filter {
                it.team1 in teamIds || it.team2 in teamIds
            }
        }

        fun matches(): List<Match> {
            return matches.values.toList()
        }

        fun save(match: Match): Match? {
            val team1 = TeamRepository.findTeamById(match.team1)
            val team2 = TeamRepository.findTeamById(match.team2)

            require(team1 != null && team2 != null) {
                "Teams with ids ($team1, $team2) could not be found."
            }

            val newTeamElos = Elo.calculateElo(t1 = team1, t2 = team2, match = match)

            TeamRepository.updateTeam(team1, newTeamElos.newEloTeam1)
            TeamRepository.updateTeam(team2, newTeamElos.newEloTeam2)

            this.matches[counter.incrementAndGet()] = match.copy(id = counter.get())

            return match
        }
    }
}