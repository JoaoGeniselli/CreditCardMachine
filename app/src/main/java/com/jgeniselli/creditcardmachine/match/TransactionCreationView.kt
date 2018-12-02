package com.jgeniselli.creditcardmachine.match

class TransactionCreationView {

    companion object {

        fun byTransactionType(type: Transaction.Type) {
            when(type) {
                Transaction.Type.DEBIT -> {}
                Transaction.Type.CREDIT -> {}
                Transaction.Type.TRANSFER -> {}
                Transaction.Type.PRO_LABORE -> {}
            }
        }
    }
}