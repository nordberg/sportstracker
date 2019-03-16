import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.Test
import pingpongtracker.business.Elo
import pingpongtracker.domain.Match
import pingpongtracker.domain.Sport
import pingpongtracker.domain.Team

class EloTest {

    @Test
    fun `test calculation of new elos where better player wins`() {
        val team1 = Team(
            id = 1L,
            playerIds = setOf(elo2400Player.id),
            elo = 2400
        )
        val team2 = Team(
            id = 2L,
            playerIds = setOf(elo2000Player.id),
            elo = 2100
        )

        val match = Match(
            id = 10L,
            sport = Sport.PINGPONG,
            team1 = 1L,
            team2 = 2L,
            scoreT1 = 2,
            scoreT2 = 0
        )

        val expectedEloPair = Elo.NewEloPair(2403, 1997)
        val resultEloPair = Elo.calculateElo(team1, team2, match)

        assertThat(resultEloPair).isEqualToComparingFieldByField(expectedEloPair)
    }

    @Test
    fun `test calculation of new elos where worse player wins`() {
        val team1 = Team(
            id = 1L,
            playerIds = setOf(elo2400Player.id),
            elo = 2400
        )
        val team2 = Team(
            id = 2L,
            playerIds = setOf(elo2000Player.id),
            elo = 2100
        )

        val match = Match(
            id = 10L,
            sport = Sport.PINGPONG,
            team1 = 1L,
            team2 = 2L,
            scoreT1 = 0,
            scoreT2 = 2
        )

        val expectedEloPair = Elo.NewEloPair(2371, 2029)
        val resultEloPair = Elo.calculateElo(team1, team2, match)

        assertThat(resultEloPair).isEqualToComparingFieldByField(expectedEloPair)
    }
}