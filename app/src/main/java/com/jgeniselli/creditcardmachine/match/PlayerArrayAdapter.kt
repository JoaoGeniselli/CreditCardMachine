package com.jgeniselli.creditcardmachine.match

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class PlayerArrayAdapter(context: Context, players: List<Player>):
        ArrayAdapter<Player>(context, android.R.layout.simple_list_item_2, players) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        val player = getItem(position)
        if (view == null) {
            view = LayoutInflater.from(parent?.context)
                    .inflate(android.R.layout.simple_list_item_2, parent, false)
        }
        view?.findViewById<TextView>(android.R.id.text1)?.text = player!!.name
        view?.findViewById<TextView>(android.R.id.text2)?.text = formatInBRRealsCurrency(player.money)
        return view!!
    }
}