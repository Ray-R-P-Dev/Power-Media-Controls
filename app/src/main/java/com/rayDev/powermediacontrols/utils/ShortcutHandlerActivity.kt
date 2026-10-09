package com.rayDev.powermediacontrols.utils

import android.app.Activity
import android.content.Intent
import android.os.Bundle

class ShortcutHandlerActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val command = intent.getStringExtra("COMMAND")
        if (command != null) {
            val broadcastIntent = Intent("com.rayDev.powermediacontrols.ACTION_TRIGGER")
            broadcastIntent.`package` = packageName
            broadcastIntent.putExtra("COMMAND", command)
            sendBroadcast(broadcastIntent)
        }
        finish()
    }
}