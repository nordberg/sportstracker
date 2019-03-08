package pingpongtracker.repositories

import pingpongtracker.domain.Player
import java.util.concurrent.atomic.AtomicLong

class PlayerRepository {

    companion object {

        private val counter = AtomicLong()

        private val players = listOf(
            Player(id = counter.incrementAndGet(), firstName = "Marcus", lastName = "Nordberg"),
            Player(id = counter.incrementAndGet(), firstName = "William", lastName = "Perkola"),
            Player(id = counter.incrementAndGet(), firstName = "Anda", lastName = "Zhang"),
            Player(id = counter.incrementAndGet(), firstName = "Martin", lastName = "Boberg")
        )

        fun players(): List<Player> {
            return players
        }

        fun findPlayerById(id: Long): Player? {
            return players.find { it.id == id }
        }
    }
}