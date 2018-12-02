package com.jgeniselli.creditcardmachine.match

interface ItemSelectionListener<T> {
    fun onItemSelected(item: T)
}

interface PositionSelectionListener {
    fun onPositionSelected(item: Int)
}