package pingpongtracker.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import pingpongtracker.domain.Team
import pingpongtracker.repositories.TeamRepository

@RestController
@RequestMapping("/teams")
class TeamController {

    @GetMapping(
        "/",
        produces = arrayOf("application/json")
    )
    fun teams(): List<Team> {
        return TeamRepository.teams()
    }

    @GetMapping(
        "/{id}",
        produces = arrayOf("application/json")
    )
    fun teams(@PathVariable(value = "id") id: Long): Team? {
        return TeamRepository.findTeamById(id)
    }

    @GetMapping(
        "/top",
        produces = arrayOf("application/json")
    )
    fun top(
        @RequestParam(value = "number") number: Int = 10,
        @RequestParam(value = "teamSize") teamSize: Int = 1
    ): List<Team> {
        return TeamRepository.getTopTeams(number, teamSize).toList()
    }
}
