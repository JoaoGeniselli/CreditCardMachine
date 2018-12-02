package com.jgeniselli.creditcardmachine.match


import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.jgeniselli.creditcardmachine.R
import kotlinx.android.synthetic.main.fragment_transaction.*

class ProLaboreFragment : BaseTransactionFragment(), AdapterView.OnItemSelectedListener {

    private var selectedPlayer: Player? = null

    override fun setupLayout() {
        val adapter = ArrayAdapter<String>(
                context!!,
                android.R.layout.simple_list_item_1,
                viewModel.players().map { it.name }
        )
        enableSpinnerA(R.string.player_name, adapter, this)
        execute.setText(R.string.to_credit_pro_labore)
    }

    override fun validateForm(): Boolean = true

    override fun executeTransaction() {
        viewModel.creditProLaboreToPlayer(selectedPlayer!!)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        selectedPlayer = viewModel.players()[position]
    }

    override fun onNothingSelected(parent: AdapterView<*>?) { }

}
