import fixtures.elo2000TeamOnePlayer
import fixtures.elo2400TeamOnePlayer
import fixtures.matchWithT1AsWinner
import fixtures.matchWithT2AsWinner
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.Test
import pingpongtracker.business.Elo

class EloTest {

    @Test
    fun `test calculation of new elos where better player wins`() {
        val team1 = elo2400TeamOnePlayer
        val team2 = elo2000TeamOnePlayer

        val match = matchWithT1AsWinner

        val expectedEloPair = Elo.NewEloPair(2403, 1997)
        val resultEloPair = Elo.calculateElo(team1, team2, match)

        assertThat(resultEloPair).isEqualToComparingFieldByField(expectedEloPair)
    }

    @Test
    fun `test calculation of new elos where worse player wins`() {
        val team1 = elo2400TeamOnePlayer
        val team2 = elo2000TeamOnePlayer

        val match = matchWithT2AsWinner

        val expectedEloPair = Elo.NewEloPair(2371, 2029)
        val resultEloPair = Elo.calculateElo(team1, team2, match)

        assertThat(resultEloPair).isEqualToComparingFieldByField(expectedEloPair)
    }
}