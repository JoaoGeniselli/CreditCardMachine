package com.jgeniselli.creditcardmachine.match

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.jgeniselli.creditcardmachine.R
import com.jgeniselli.creditcardmachine.match.Transaction.Type.*
import com.jgeniselli.creditcardmachine.replaceFragment

class TransactionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val transactionTypeId = intent.getIntExtra(ARG_TYPE_ID, 0)
        val type = Transaction.Type.byId(transactionTypeId)
                ?: throw IllegalArgumentException("Transaction Type is invalid")
        title = getString(type.typeName)
        setupFragmentFromType(type)
    }

    private fun setupFragmentFromType(type: Transaction.Type) {
        when(type) {
            DEBIT -> replaceFragment(DebitFragment())
            CREDIT -> replaceFragment(CreditFragment())
            TRANSFER -> replaceFragment(TransferFragment())
            PRO_LABORE -> replaceFragment(ProLaboreFragment())
            else -> {}
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        replaceFragment(fragment, R.id.frame)
    }

    companion object {
        private const val ARG_TYPE_ID = "TRANSACTION_TYPE_ID"

        fun makeIntentForTransaction(context: Context, type: Transaction.Type): Intent {
            val intent = Intent(context, TransactionActivity::class.java)
            intent.putExtra(ARG_TYPE_ID, type.id)
            return intent
        }
    }
}
