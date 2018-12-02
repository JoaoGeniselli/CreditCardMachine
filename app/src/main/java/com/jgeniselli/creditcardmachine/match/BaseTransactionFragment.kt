package com.jgeniselli.creditcardmachine.match

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.SpinnerAdapter
import com.jgeniselli.creditcardmachine.R
import com.jgeniselli.creditcardmachine.playCashRegisterSound
import kotlinx.android.synthetic.main.fragment_transaction.*

abstract class BaseTransactionFragment : Fragment() {

    protected lateinit var viewModel: MatchViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_transaction, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MatchViewModel::class.java)
        setupLayout()
        execute.setOnClickListener {
            executeIfPossible()
        }
    }

    protected abstract fun setupLayout()

    private fun executeIfPossible() {
        if (formContainsErrors()) return
        executeTransaction()
        playCashRegisterSound(context!!)
        activity?.finish()
    }

    private fun formContainsErrors(): Boolean = !validateForm()

    protected abstract fun validateForm(): Boolean

    protected abstract fun executeTransaction()

    protected fun enableSpinnerA(@StringRes labelId: Int,
                                 adapter: SpinnerAdapter,
                                 listener: AdapterView.OnItemSelectedListener) {
        text_view_a.visibility = View.VISIBLE
        text_view_a.setText(labelId)
        spinner_a.visibility = View.VISIBLE
        spinner_a.adapter = adapter
        spinner_a.onItemSelectedListener = listener
    }

    protected fun enableSpinnerB(@StringRes labelId: Int,
                                 adapter: SpinnerAdapter,
                                 listener: AdapterView.OnItemSelectedListener) {
        text_view_b.visibility = View.VISIBLE
        text_view_b.setText(labelId)
        spinner_b.visibility = View.VISIBLE
        spinner_b.adapter = adapter
        spinner_b.onItemSelectedListener = listener
    }

    protected fun enableValueText() {
        value_input_layout.visibility = View.VISIBLE
    }









}