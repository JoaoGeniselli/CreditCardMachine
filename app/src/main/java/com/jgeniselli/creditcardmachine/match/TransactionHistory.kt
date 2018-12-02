package com.jgeniselli.creditcardmachine.match

import java.util.*

class TransactionHistory(private val maxHistorySize: Int) {

    private val history: Stack<Transaction> = Stack()

    fun add(transaction: Transaction) {
        history.push(transaction)
        while (history.size > maxHistorySize) history.removeAt(0)
    }

    fun history(): List<Transaction> {
        val historyList = history.toMutableList()
        historyList.reverse()
        return historyList
    }
}