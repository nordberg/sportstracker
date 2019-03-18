package pingpongtracker.controllers

import org.springframework.web.bind.annotation.*
import pingpongtracker.domain.Match
import pingpongtracker.repositories.MatchRepository

@RestController
@RequestMapping("/matches")
class MatchController {
    @GetMapping(
        "/",
        produces = arrayOf("application/json")
    )
    fun matches(): List<Match> {
        return MatchRepository.matches()
    }

    @PostMapping(
        "/",
        produces = arrayOf("application/json"),
        consumes = arrayOf("application/json")
    )
    fun addMatch(@RequestBody match: Match): Match? {
        return MatchRepository.save(match)
    }
}