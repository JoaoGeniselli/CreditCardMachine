package com.jgeniselli.creditcardmachine.match


import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import com.jgeniselli.creditcardmachine.R
import kotlinx.android.synthetic.main.fragment_simple_list.*

class PlayerListFragment : Fragment() {

    lateinit var adapter: ArrayAdapter<Player>

    private lateinit var viewModel: MatchViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_simple_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (context == null) return
        adapter = PlayerArrayAdapter(context!!, viewModel.players())
        list_view.adapter = adapter
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        viewModel = ViewModelProviders.of(activity!!).get(MatchViewModel::class.java)
    }
}
