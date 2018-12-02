package com.jgeniselli.creditcardmachine.match

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.jgeniselli.creditcardmachine.R
import kotlinx.android.synthetic.main.activity_transaction_history.*

class TransactionHistoryActivity : AppCompatActivity() {

    private lateinit var viewModel: MatchViewModel

    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_history)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewModel = ViewModelProviders.of(this).get(MatchViewModel::class.java)
        adapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                viewModel.history().map { it.toString() }
        )
        list_view.adapter = adapter
    }
}
