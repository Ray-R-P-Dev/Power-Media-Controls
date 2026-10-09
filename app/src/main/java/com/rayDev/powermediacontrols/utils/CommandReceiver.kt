package com.rayDev.powermediacontrols.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.rayDev.powermediacontrols.BuildConfig

class CommandReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (BuildConfig.DEBUG) {
            Log.d("CommandReceiver", "Received broadcast: ${intent.action}")
        }

        if (intent.action == "com.rayDev.powermediacontrols.ACTION_TRIGGER") {
            val command = intent.getStringExtra("COMMAND")
            if (BuildConfig.DEBUG) {
                Log.d("CommandReceiver", "Received command: $command")
            }

            when (command) {
                "MEDIA_1" -> {
                    playMedia(context)
                }
                "MEDIA_2" -> {
                    togglePlayPause(context)
                }
                "MEDIA_3" -> {
                    stopMedia(context)
                }
                "MEDIA_4" -> {
                    previousTrack(context)
                }
                "MEDIA_5" -> {
                    nextTrack(context)
                }
                // Add more cases for other commands if needed
            }
        }
    }
}