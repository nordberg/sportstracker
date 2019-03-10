import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.Test
import pingpongtracker.business.Elo
import pingpongtracker.domain.Match
import pingpongtracker.domain.Sport

class EloTest {

    @Test
    fun `test calculation of new elos where better player wins`() {
        val player1 = elo2400Player
        val player2 = elo2000Player

        val match = Match(
            id = 10L,
            sport = Sport.PINGPONG,
            team1 = listOf(player1),
            team2 = listOf(player2),
            scoreT1 = 2,
            scoreT2 = 0
        )

        val newEloT1 = 2403
        val newEloT2 = 1997

        val expectedEloPair = Pair(newEloT1, newEloT2)
        val resultEloPair = Elo.calculateElo(player1, player2, match)

        assertThat(resultEloPair).isEqualToComparingFieldByField(expectedEloPair)
    }

    @Test
    fun `test calculation of new elos where worse player wins`() {
        val player1 = elo2400Player
        val player2 = elo2000Player

        val match = Match(
            id = 10L,
            sport = Sport.PINGPONG,
            team1 = listOf(player1),
            team2 = listOf(player2),
            scoreT1 = 0,
            scoreT2 = 2
        )

        val newEloT1 = 2371
        val newEloT2 = 2029

        val expectedEloPair = Pair(newEloT1, newEloT2)
        val resultEloPair = Elo.calculateElo(player1, player2, match)

        assertThat(resultEloPair).isEqualToComparingFieldByField(expectedEloPair)
    }
}