package com.jgeniselli.creditcardmachine.match

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jgeniselli.creditcardmachine.match.TransactionRecyclerViewAdapter.ViewHolder

class TransactionRecyclerViewAdapter(
        private val listener: ItemSelectionListener<Transaction.Type>
) : RecyclerView.Adapter<ViewHolder>() {

    val values = Transaction.Type.values()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(android.R.layout.simple_list_item_1, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return values.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val value = values[position]
        viewHolder.setText(value.typeName)
        viewHolder.setOnClickListener(View.OnClickListener {
            listener.onItemSelected(value)
        })
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private fun textView() = itemView.findViewById<TextView>(android.R.id.text1)

        fun setText(text: Int) {
            textView().setText(text)
        }

        fun setOnClickListener(listener: View.OnClickListener) {
            itemView.setOnClickListener(listener)
        }
    }


}

