package com.jgeniselli.creditcardmachine.match

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import com.jgeniselli.creditcardmachine.R
import com.jgeniselli.creditcardmachine.replaceFragment
import kotlinx.android.synthetic.main.activity_match.*

class MatchActivity : AppCompatActivity() {

    private lateinit var viewModel: MatchViewModel
    private lateinit var playersFragment: PlayerListFragment
    private lateinit var transactionsFragment: TransactionListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)

        viewModel = ViewModelProviders.of(this).get(MatchViewModel::class.java)
        playersFragment = PlayerListFragment()
        transactionsFragment = TransactionListFragment()

        bottom_navigation.setOnNavigationItemSelectedListener { swithFragment(it) }
    }

    override fun onStart() {
        super.onStart()
        bottom_navigation.selectedItemId = R.id.players
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.match_options, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.transaction_history -> {
                showTransactionsHistory()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun showTransactionsHistory() {
        val intent = Intent(this, TransactionHistoryActivity::class.java)
        startActivity(intent)
    }

    private fun swithFragment(selectedItem: MenuItem): Boolean {
        when(selectedItem.itemId) {
            R.id.players -> replaceFragment(playersFragment)
            else -> replaceFragment(transactionsFragment)
        }
        return true
    }

    private fun replaceFragment(fragment: Fragment) {
        replaceFragment(fragment, R.id.frame)
    }


}
