package com.jgeniselli.creditcardmachine.match

import java.text.NumberFormat
import java.util.*

fun formatInBRRealsCurrency(value: Double): String? {
    val locale = Locale("pt", "br")
    val formatter = NumberFormat.getCurrencyInstance(locale)
    return formatter.format(value)
}