package pingpongtracker.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import pingpongtracker.domain.Team
import pingpongtracker.repositories.TeamRepository

@RestController
class TeamController {

    @GetMapping("/teams/")
    fun teams(): List<Team> {
        return TeamRepository.teams()
    }

    @GetMapping("/teams/{id}")
    fun teams(@PathVariable(value = "id") id: Long): Team? {
        return TeamRepository.findTeamById(id)
    }
}
