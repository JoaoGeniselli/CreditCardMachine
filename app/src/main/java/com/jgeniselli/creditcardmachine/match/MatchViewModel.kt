package com.jgeniselli.creditcardmachine.match

import android.arch.lifecycle.ViewModel

class MatchViewModel : ViewModel() {

    val match = Match.getDefault()
    private val creditCardMachine = match.creditCardMachine

    fun players(): List<Player> = match.players

    fun debitFromPlayer(player: Player, value: Double) {
        executeTransaction(
                Transaction.newDebit(player, value)
        )
    }

    private fun executeTransaction(transaction: Transaction) {
        creditCardMachine.executeTransaction(transaction)
    }

    fun creditToPlayer(player: Player, value: Double) {
        executeTransaction(
                Transaction.newCredit(player, value)
        )
    }

    fun transferBetweenPlayers(sourcePlayer: Player, destinyPlayer: Player, value: Double) {
        executeTransaction(
                Transaction.newTransfer(sourcePlayer, destinyPlayer, value)
        )
    }

    fun creditProLaboreToPlayer(player: Player) {
        executeTransaction(
                Transaction.newProLabore(player)
        )
    }

    fun history() = creditCardMachine.history()

}