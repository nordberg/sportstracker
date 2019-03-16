package pingpongtracker.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
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

    @GetMapping("/teams/top/")
    fun top(@RequestParam(value = "number") number: Int = 10,
            @RequestParam(value = "teamSize") teamSize: Int = 1): List<Team> {
        return TeamRepository.getTopTeams(number, teamSize).toList()
    }
}
