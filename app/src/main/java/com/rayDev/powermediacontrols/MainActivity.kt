@file:Suppress("DEPRECATION")

package com.rayDev.powermediacontrols

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.core.content.edit
import com.rayDev.powermediacontrols.ui.theme.PowerMediaControlsTheme
import com.rayDev.powermediacontrols.uiElements.AppPopup
import com.rayDev.powermediacontrols.uiElements.TheTopBar

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val prefs = getSharedPreferences("app_prefs", MODE_PRIVATE)
        val isFirstLaunch = prefs.getBoolean("is_first_launch", true)

        setContent {
            PowerMediaControlsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var showFirstLaunchPopup by remember { mutableStateOf(isFirstLaunch) }

                    Box(modifier = Modifier.fillMaxSize()) {
                        TheTopBar()

                        if (showFirstLaunchPopup) {
                            AppPopup(
                                show = true,
                                onDismiss = {
                                    showFirstLaunchPopup = false
                                    prefs.edit { putBoolean("is_first_launch", false) }
                                },
                                onConfirm = {
                                    showFirstLaunchPopup = false
                                    prefs.edit { putBoolean("is_first_launch", false) }
                                },
                                header = stringResource(R.string.notice),
                                body = stringResource(R.string.First_Screen_Notice),
                            )
                        }
                    }
                }
            }
        }
    }
}