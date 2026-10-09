package com.rayDev.powermediacontrols.utils

import android.content.Context
import android.media.AudioManager
import android.os.SystemClock
import android.view.KeyEvent

private fun dispatchMediaKeyEvent(context: Context, keyCode: Int) {
    val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    val eventTime = SystemClock.uptimeMillis()

    val downEvent = KeyEvent(eventTime, eventTime, KeyEvent.ACTION_DOWN, keyCode, 0)
    audioManager.dispatchMediaKeyEvent(downEvent)

    val upEvent = KeyEvent(eventTime, eventTime, KeyEvent.ACTION_UP, keyCode, 0)
    audioManager.dispatchMediaKeyEvent(upEvent)
}

fun togglePlayPause(context: Context) {
    dispatchMediaKeyEvent(context, KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE)
}

fun playMedia(context: Context) {
    dispatchMediaKeyEvent(context, KeyEvent.KEYCODE_MEDIA_PLAY)
}

fun nextTrack(context: Context) {
    dispatchMediaKeyEvent(context, KeyEvent.KEYCODE_MEDIA_NEXT)
}

fun previousTrack(context: Context) {
    dispatchMediaKeyEvent(context, KeyEvent.KEYCODE_MEDIA_PREVIOUS)
}

fun stopMedia(context: Context) {
    dispatchMediaKeyEvent(context, KeyEvent.KEYCODE_MEDIA_STOP)
}
// Add more functions as needed for other media controls here
