package com.jgeniselli.creditcardmachine.match

import java.util.*

class Match private constructor(private val _players: List<Player>) {

    val players: List<Player>
        get() = Collections.unmodifiableList(_players)

    val creditCardMachine = CreditCardMachine()

    fun reset() {
        players.forEach {
            it.resetMoney()
        }
    }

    companion object {
        const val DEFAULT_MONEY_PER_PLAYER = 25000.00
        private var DEFAULT_INSTANCE: Match? = null

        fun setupNewMatchForPlayers(players: List<Player>): Match {
            DEFAULT_INSTANCE = Match(players)
            return DEFAULT_INSTANCE!!
        }

        fun getDefault(): Match {
            if (DEFAULT_INSTANCE == null)
                throw IllegalStateException("You must create a Match before use it")
            return DEFAULT_INSTANCE!!
        }
    }
}