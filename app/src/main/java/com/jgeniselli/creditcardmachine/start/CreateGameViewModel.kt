package com.jgeniselli.creditcardmachine.start

import android.arch.lifecycle.ViewModel
import com.jgeniselli.creditcardmachine.match.Match
import com.jgeniselli.creditcardmachine.match.Player

class CreateGameViewModel : ViewModel() {

    val players: MutableList<String> = ArrayList()

    fun canCreateNewPlayer() = players.size < 6

    fun addPlayer(name: String) {
        if (name.isEmpty()) throw IllegalArgumentException("Name must not be empty")
        players.add(name)
    }

    fun removePlayer(name: String) {
        if (!players.contains(name)) throw IllegalArgumentException("Player not found")
        players.remove(name)
    }

    fun validatePlayersForNewMatch() {
        if (players.size < 2 || players.size > 6)
            throw ImpossibleMatchException()
    }

    fun createMatch() {
        Match.setupNewMatchForPlayers(Player.createAll(players, Match.DEFAULT_MONEY_PER_PLAYER))
    }

    inner class ImpossibleMatchException: RuntimeException()
}
//
//fun <T> MutableLiveData<T>.notify() {
//    this.value = this.value
//}