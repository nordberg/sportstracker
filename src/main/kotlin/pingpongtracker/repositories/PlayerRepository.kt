package pingpongtracker.repositories

import pingpongtracker.domain.Player
import java.util.concurrent.atomic.AtomicLong

class PlayerRepository {

    companion object {

        private val counter = AtomicLong()

        private val players = mutableMapOf(
            counter.incrementAndGet() to Player(id = counter.get(), firstName = "Marcus", lastName = "Nordberg", elo = (1500..2500).random()),
            counter.incrementAndGet() to Player(id = counter.get(), firstName = "William", lastName = "Perkola", elo = (1500..2500).random()),
            counter.incrementAndGet() to Player(id = counter.get(), firstName = "Anda", lastName = "Zhang", elo = (1500..2500).random()),
            counter.incrementAndGet() to Player(id = counter.get(), firstName = "Martin", lastName = "Boberg", elo = (1500..2500).random())
        )

        fun players(): List<Player> {
            return players.values.toList()
        }

        fun findPlayerById(id: Long): Player? {
            return players[id]
        }

        fun findPlayersById(ids: List<Long>): List<Player> {
            return players().filter { it.id in ids }
        }

        fun updatePlayer(player: Player, elo: Int) {
            players[player.id] = player.copy(
                elo = elo
            )
        }

        fun getTopPlayers(number: Int): List<Player> {
            return this.players().sortedBy {
                it.elo
            }.reversed().take(number)
        }

        fun save(player: Player): Player? {
            this.players[counter.incrementAndGet()] = player.copy(
                id = counter.get()
            )

            return this.players[counter.get()]
        }
    }
}