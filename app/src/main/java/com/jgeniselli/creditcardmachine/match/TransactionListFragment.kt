package com.jgeniselli.creditcardmachine.match

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jgeniselli.creditcardmachine.R
import kotlinx.android.synthetic.main.fragment_simple_recycler.*

class TransactionListFragment : Fragment(), ItemSelectionListener<Transaction.Type> {
    private lateinit var adapter: TransactionRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_simple_recycler, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        recycler_view.adapter = adapter
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        adapter = TransactionRecyclerViewAdapter(this)
    }

    override fun onItemSelected(item: Transaction.Type) {
        if (!isAdded) return
        val intent = TransactionActivity.makeIntentForTransaction(activity!!, item)
        activity?.startActivity(intent)
    }


}
