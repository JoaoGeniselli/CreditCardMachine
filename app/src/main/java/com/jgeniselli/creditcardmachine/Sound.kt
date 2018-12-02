package com.jgeniselli.creditcardmachine

import android.content.Context
import android.media.MediaPlayer

fun playCashRegisterSound(context: Context) {
    MediaPlayer.create(context, R.raw.cash_register_a).start()
}