package pingpongtracker.controllers

import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.Test
import pingpongtracker.repositories.TeamRepository

class TestTeamController {

    @Test
    fun `get top teams`() {
        val topThreeTeamsOfSize1 = TeamRepository.getTopTeams(3, 1)

        assertThat(topThreeTeamsOfSize1[0].elo).isGreaterThan(topThreeTeamsOfSize1[1].elo)
        assertThat(topThreeTeamsOfSize1[1].elo).isGreaterThan(topThreeTeamsOfSize1[2].elo)

        topThreeTeamsOfSize1.forEach {
            assertThat(it.playerIds.size).isEqualTo(1)
        }
    }
}