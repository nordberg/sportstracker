package pingpongtracker.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import pingpongtracker.domain.Player
import pingpongtracker.repositories.PlayerRepository
import java.util.concurrent.atomic.AtomicLong

@RestController
class PlayerController {
    val counter = AtomicLong()

    @GetMapping("/players/")
    fun players(): List<Player> {
        return PlayerRepository.players()
    }

    @GetMapping("/player/{id}")
    fun player(@PathVariable(value = "id") id: Long): Player? {
        return PlayerRepository.findPlayerById(id)
    }
}