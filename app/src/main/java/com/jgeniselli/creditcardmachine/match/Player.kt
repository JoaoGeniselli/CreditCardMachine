package com.jgeniselli.creditcardmachine.match

class Player(val name: String, var money: Double) {

    private val initialMoney = money

    fun debit(value: Double) {
        money -= value
    }

    fun credit(value: Double) {
        money += value
    }

    fun resetMoney() {
        money = initialMoney
    }

    companion object {
        fun createAll(playerNames: List<String>, initialMoney: Double) =
                playerNames.map { Player(it, initialMoney) }
    }
}