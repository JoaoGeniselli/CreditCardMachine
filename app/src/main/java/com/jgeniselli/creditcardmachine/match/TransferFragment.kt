package com.jgeniselli.creditcardmachine.match

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.jgeniselli.creditcardmachine.R
import com.jgeniselli.creditcardmachine.common.extensions.isEmpty
import kotlinx.android.synthetic.main.fragment_transaction.*

class TransferFragment : BaseTransactionFragment(), AdapterView.OnItemSelectedListener {

    private lateinit var selectedSourcePlayer: Player
    private lateinit var selectedDestinyPlayer: Player

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_transaction, container, false)
    }

    override fun setupLayout() {
        val adapter = ArrayAdapter<String>(
                context!!,
                android.R.layout.simple_list_item_1,
                viewModel.players().map { it.name }
        )
        enableSpinnerA(R.string.source_player_name, adapter, this)
        enableSpinnerB(R.string.destiny_player_name, adapter, this)
        enableValueText()
        execute.setText(R.string.to_transfer_value)
    }

    override fun validateForm(): Boolean {
        if (selectedSourcePlayer == selectedDestinyPlayer) {
            showPlayerSelectionError()
            return false
        }
        if (value_text.isEmpty()) {
            value_text.error = getString(R.string.value_cant_be_empty)
            return false
        }
        return true
    }

    override fun executeTransaction() {
        viewModel.transferBetweenPlayers(
                selectedSourcePlayer,
                selectedDestinyPlayer,
                value_text.text.toString().toDouble()
        )
    }

    private fun showPlayerSelectionError() {
        AlertDialog.Builder(context)
                .setTitle(R.string.warning)
                .setMessage(R.string.selected_players_must_not_be_the_same)
                .setNeutralButton(android.R.string.ok, null)
                .show()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (parent) {
            spinner_a -> selectedSourcePlayer = viewModel.players()[position]
            else -> selectedDestinyPlayer = viewModel.players()[position]
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}


}
