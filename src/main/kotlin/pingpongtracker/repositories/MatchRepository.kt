package pingpongtracker.repositories

import pingpongtracker.domain.Match
import pingpongtracker.domain.Sport
import java.util.concurrent.atomic.AtomicLong

class MatchRepository {
    companion object {
        private val counter = AtomicLong()

        private val matches = listOf(
            Match(
                id = counter.incrementAndGet(),
                team1 = listOf(PlayerRepository.findPlayerById(1)!!),
                team2 = listOf(PlayerRepository.findPlayerById(2)!!),
                scoreT1 = 1,
                scoreT2 = 2,
                sport = Sport.EIGHTBALL

            ),
            Match(
                id = counter.incrementAndGet(),
                team1 = listOf(PlayerRepository.findPlayerById(2)!!),
                team2 = listOf(PlayerRepository.findPlayerById(3)!!),
                scoreT1 = 0,
                scoreT2 = 2,
                sport = Sport.PINGPONG
            ),
            Match(
                id = counter.incrementAndGet(),
                team1 = listOf(PlayerRepository.findPlayerById(1)!!),
                team2 = listOf(PlayerRepository.findPlayerById(3)!!),
                scoreT1 = 2,
                scoreT2 = 1,
                sport = Sport.PINGPONG
            )
        )

        fun matches(): List<Match> {
            return matches
        }

        fun findMatchById(id: Long): Match? {
            return matches.find { it.id == id }
        }

        fun findMatchesBySport(sport: Sport): List<Match> {
            return matches.filter {
                it.sport == sport
            }
        }
    }
}