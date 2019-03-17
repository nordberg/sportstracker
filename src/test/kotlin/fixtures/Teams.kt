package fixtures

import pingpongtracker.domain.Team

val elo2000TeamOnePlayer = Team(
    id = 1L,
    playerIds = setOf(1L),
    elo = 2000
)

val elo2400TeamOnePlayer = Team(
    id = 2L,
    playerIds = setOf(2L),
    elo = 2400
)

val elo2000TeamTwoPlayers = Team(
    id = 1L,
    playerIds = setOf(2L, 3L),
    elo = 2000
)

val elo2100TeamTwoPlayers = Team(
    id = 1L,
    playerIds = setOf(3L, 4L),
    elo = 2100
)
