package com.jgeniselli.creditcardmachine.match


import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.jgeniselli.creditcardmachine.R
import com.jgeniselli.creditcardmachine.common.extensions.isEmpty
import kotlinx.android.synthetic.main.fragment_transaction.*

class CreditFragment : BaseTransactionFragment(), AdapterView.OnItemSelectedListener {

    private var selectedPlayer: Player? = null

    override fun setupLayout() {
        val adapter = ArrayAdapter<String>(
                context!!,
                android.R.layout.simple_list_item_1,
                viewModel.players().map { it.name }
        )
        enableSpinnerA(R.string.player_name, adapter, this)
        enableValueText()
        execute.setText(R.string.to_credit)
    }

    override fun validateForm(): Boolean {
        if (value_text.isEmpty()) {
            value_text.error = getString(R.string.value_cant_be_empty)
            return false
        }
        return true
    }

    override fun executeTransaction() {
        viewModel.creditToPlayer(
                selectedPlayer!!,
                value_text.text.toString().toDouble()
        )
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        selectedPlayer = viewModel.players()[position]
    }

    override fun onNothingSelected(parent: AdapterView<*>?) { }


}
