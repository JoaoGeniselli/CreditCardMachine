package com.jgeniselli.creditcardmachine.common.extensions

import android.widget.EditText

fun EditText.isEmpty() = text == null || text.toString().isEmpty()