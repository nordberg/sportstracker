package pingpongtracker.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import pingpongtracker.domain.Match
import pingpongtracker.repositories.MatchRepository
import java.util.concurrent.atomic.AtomicLong

@RestController
class MatchController {
    val counter = AtomicLong()

    @GetMapping("/matches/")
    fun matches(): List<Match> {
        return MatchRepository.matches()
    }

}