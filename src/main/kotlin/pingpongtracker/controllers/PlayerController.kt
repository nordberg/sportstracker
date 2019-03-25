package pingpongtracker.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import pingpongtracker.domain.Match
import pingpongtracker.domain.Player
import pingpongtracker.repositories.MatchRepository
import pingpongtracker.repositories.PlayerRepository

@RestController
@RequestMapping("/players")
class PlayerController {

    @GetMapping("/")
    fun players(): List<Player> {
        return PlayerRepository.players()
    }

    @PostMapping(
        "/",
        produces = arrayOf("application/json"),
        consumes = arrayOf("application/json")
    )
    fun createPlayer(@RequestBody player: Player): Player? {
        return PlayerRepository.save(player)
    }

    @GetMapping("/{id}")
    fun player(@PathVariable(value = "id") id: Long): Player? {
        return PlayerRepository.findPlayerById(id)
    }

    @GetMapping("/{id}/matches")
    fun matchesByPlayer(@PathVariable(value = "id") id: Long): List<Match> {
        return MatchRepository.matchesByPlayerId(id)
    }

    @GetMapping("/top/elo")
    fun top(@RequestParam(value = "number") number: Int = 10): List<Player> {
        return PlayerRepository.getTopPlayers(number).toList()
    }
}