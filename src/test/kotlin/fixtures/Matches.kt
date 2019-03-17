package fixtures

import pingpongtracker.domain.Match
import pingpongtracker.domain.Sport
import java.time.LocalDate

val matchWithT1AsWinner = Match(
    id = 1L,
    team1 = 2L,
    team2 = 3L,
    scoreT1 = 3,
    scoreT2 = 1,
    date = LocalDate.of(2018,1,1),
    sport = Sport.PINGPONG
)

val matchWithT2AsWinner = Match(
    id = 1L,
    team1 = 2L,
    team2 = 3L,
    scoreT1 = 1,
    scoreT2 = 3,
    date = LocalDate.of(2018,1,1),
    sport = Sport.PINGPONG
)
