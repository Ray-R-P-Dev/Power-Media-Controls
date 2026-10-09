package com.rayDev.powermediacontrols.utils

import android.content.Context

object ChangelogUtils {
    fun getChangelog(context: Context): String {
        return try {
            context.assets.open("Changelog.txt").bufferedReader().use { it.readText() }
        } catch (_: Exception) {
            context.getString(com.rayDev.powermediacontrols.R.string.changelog_not_available)
        }
    }
}
