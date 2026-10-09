package com.rayDev.powermediacontrols.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rayDev.powermediacontrols.R
import com.rayDev.powermediacontrols.ui.theme.PowerMediaControlsTheme
import com.rayDev.powermediacontrols.utils.nextTrack
import com.rayDev.powermediacontrols.utils.playMedia
import com.rayDev.powermediacontrols.utils.previousTrack
import com.rayDev.powermediacontrols.utils.stopMedia
import com.rayDev.powermediacontrols.utils.togglePlayPause
import com.rayDev.powermediacontrols.uiElements.ActionsList
import com.rayDev.powermediacontrols.uiElements.InfoCard

@Preview(showBackground = true)
@Composable
fun MiddleScreen() {
    val context = LocalContext.current
    val playString = stringResource(R.string.ActionPlay)
    val togglePlayPauseString = stringResource(R.string.ActionTogglePlayPause)
    val stopString = stringResource(R.string.ActionStop)
    val previousString = stringResource(R.string.ActionPrevious)
    val nextString = stringResource(R.string.ActionNext)

    val actionFeatures = remember(playString, togglePlayPauseString, nextString, previousString, stopString) {
        listOf(
            playString,
            togglePlayPauseString,
            previousString,
            nextString,
            stopString
        )
    }
    PowerMediaControlsTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)
            ) {
                InfoCard()

                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    LazyColumn {
                        items(
                            items = actionFeatures,
                            key = { it }
                        ) { feature ->
                            ActionsList(
                                feature = feature,
                                leftIcon = when (feature) {
                                    playString -> R.drawable.centerplaycompressed
                                    togglePlayPauseString -> R.drawable.better_playpause
                                    nextString -> R.drawable.final_next_track
                                    previousString -> R.drawable.final_previous_track
                                    stopString -> R.drawable.final_stop_playback
                                    else -> R.drawable.better_playpause
                                }
                            ) {
                                when (feature) {
                                    playString -> playMedia(context)
                                    togglePlayPauseString -> togglePlayPause(context)
                                    stopString -> stopMedia(context)
                                    previousString -> previousTrack(context)
                                    nextString -> nextTrack(context)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}