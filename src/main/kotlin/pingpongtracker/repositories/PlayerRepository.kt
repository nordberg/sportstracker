package pingpongtracker.repositories

import pingpongtracker.domain.Player
import java.util.concurrent.atomic.AtomicLong

class PlayerRepository {

    companion object {

        private val counter = AtomicLong()

        private val players = mutableMapOf(
            counter.incrementAndGet() to Player(id = counter.get(), firstName = "Marcus", lastName = "Nordberg", elo = 1000),
            counter.incrementAndGet() to Player(id = counter.get(), firstName = "William", lastName = "Perkola", elo = 1000),
            counter.incrementAndGet() to Player(id = counter.get(), firstName = "Anda", lastName = "Zhang", elo = 1000),
            counter.incrementAndGet() to Player(id = counter.get(), firstName = "Martin", lastName = "Boberg", elo = 1000)
        )

        fun players(): List<Player> {
            return players.values.toList()
        }

        fun findPlayerById(id: Long): Player? {
            return players[id]
        }
    }
}