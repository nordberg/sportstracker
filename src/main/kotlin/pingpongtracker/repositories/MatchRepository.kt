package pingpongtracker.repositories

import pingpongtracker.business.Elo
import pingpongtracker.domain.Match
import pingpongtracker.domain.Sport
import java.util.concurrent.atomic.AtomicLong
import javax.validation.constraints.Null

class MatchRepository {
    companion object {
        private val counter = AtomicLong()

        private val matches = mutableMapOf(
            counter.incrementAndGet() to Match(
                id = counter.get(),
                team1 = listOf(1),
                team2 = listOf(2),
                scoreT1 = 1,
                scoreT2 = 2,
                sport = Sport.EIGHTBALL

            ),
            counter.incrementAndGet() to Match(
                id = counter.get(),
                team1 = listOf(2),
                team2 = listOf(3),
                scoreT1 = 0,
                scoreT2 = 2,
                sport = Sport.PINGPONG
            ),
            counter.incrementAndGet() to Match(
                id = counter.get(),
                team1 = listOf(1),
                team2 = listOf(3),
                scoreT1 = 2,
                scoreT2 = 1,
                sport = Sport.PINGPONG
            )
        )

        fun matches(): List<Match> {
            return matches.values.toList()
        }

        fun save(match: Match): Match? {

            val player1 = PlayerRepository.findPlayerById(match.team1.first())
            val player2 = PlayerRepository.findPlayerById(match.team2.first())

            if (player1 == null) {
                return null
            }
            if (player2 == null) {
                return null
            }

            val newPlayerElos = Elo.calculateElo(p1 = player1, p2 = player2, match = match)

            PlayerRepository.updatePlayer(player1, newPlayerElos.newEloPlayer1)
            PlayerRepository.updatePlayer(player2, newPlayerElos.newEloPlayer2)

            this.matches[counter.incrementAndGet()] = match.copy(id = counter.get())

            return match
        }
    }
}