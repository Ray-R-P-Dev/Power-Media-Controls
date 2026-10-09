package com.rayDev.powermediacontrols.screens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.google.android.gms.oss.licenses.v2.OssLicensesMenuActivity

class OpenSourceLicensesScreen : ComponentActivity() {
    companion object {
        private const val EXTRA_TITLE = "extra_title"
        private const val EXTRA_CONTENT = "extra_content"
        private const val EXTRA_IS_LICENSES = "extra_is_licenses"

        fun createIntent(context: Context, title: String, content: String, isLicenses: Boolean = false): Intent {
            return Intent(context, OpenSourceLicensesScreen::class.java).apply {
                putExtra(EXTRA_TITLE, title)
                putExtra(EXTRA_CONTENT, content)
                putExtra(EXTRA_IS_LICENSES, isLicenses)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, OssLicensesMenuActivity::class.java))
        finish()
    }
}
