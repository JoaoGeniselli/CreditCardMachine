package com.jgeniselli.creditcardmachine.match

class CreditCardMachine {

    private val history: TransactionHistory = TransactionHistory(20)

    fun executeTransaction(transaction: Transaction) {
        transaction.execute()
        history.add(transaction)
    }

    fun history() = history.history()
}