package pingpongtracker.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import pingpongtracker.domain.Player
import pingpongtracker.repositories.PlayerRepository

@RestController
class PlayerController {

    @GetMapping("/players/")
    fun players(): List<Player> {
        return PlayerRepository.players()
    }

    @GetMapping("/player/{id}")
    fun player(@PathVariable(value = "id") id: Long): Player? {
        return PlayerRepository.findPlayerById(id)
    }

    @GetMapping("/top/elo")
    fun top(@RequestParam(value = "number") number: Int = 10): List<Player> {
        return PlayerRepository.getTopPlayers(number).toList()
    }
}