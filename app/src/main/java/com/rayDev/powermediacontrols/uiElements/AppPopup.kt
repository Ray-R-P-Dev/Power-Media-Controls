package com.rayDev.powermediacontrols.uiElements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rayDev.powermediacontrols.R
import com.rayDev.powermediacontrols.screens.OpenSourceLicensesScreen
import com.rayDev.powermediacontrols.utils.ChangelogUtils

@Composable
fun AppPopup(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: (() -> Unit)? = null, // Optional: Shows "Ok" button if provided
    header: String,
    body: String,
    showExtraLinks: Boolean = false, // Optional: Shows Privacy/Licenses/Changelog links if provided
) {
    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current
    val linkColor = if (isSystemInDarkTheme()) Color(0xFF64B5F6) else Color(0xFF1976D2)

    val openSourceLicensesTitle = stringResource(R.string.open_source_licenses)

    if (show) {
        AlertDialog(
            modifier = Modifier.fillMaxWidth(0.9f),
            onDismissRequest = onDismiss,
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.onSurface,
            textContentColor = MaterialTheme.colorScheme.onSurface,
            title = {
                Text(
                    text = header,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = body,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = if (showExtraLinks) 16.dp else 0.dp),
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    if (showExtraLinks) {
                        // Privacy Policy Link
                        Text(
                            text = stringResource(R.string.privacy_policy),
                            color = linkColor,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center,
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clickable {
                                    onDismiss()
                                    uriHandler.openUri("https://sites.google.com/view/power-media-controls-privacy/in%C3%ADcio?authuser=1")
                                }
                        )

                        // Open Source Licenses
                        Text(
                            text = openSourceLicensesTitle,
                            color = linkColor,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center,
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clickable {
                                    onDismiss()
                                    context.startActivity(
                                        OpenSourceLicensesScreen.createIntent(
                                            context,
                                            openSourceLicensesTitle,
                                            "",
                                            isLicenses = true
                                        )
                                    )
                                }
                        )

                        // Tabler Icons Attribution
                        Text(
                            text = "Icons by Tabler Icons",
                            color = linkColor,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center,
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clickable {
                                    onDismiss()
                                    uriHandler.openUri("https://tabler.io/icons")
                                }
                        )

                        // Changelog (Popup)
                        val showChangelog = remember { mutableStateOf(value = false) }
                        Text(
                            text = stringResource(R.string.changelog),
                            color = linkColor,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center,
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clickable {
                                    showChangelog.value = true
                                }
                        )

                        if (showChangelog.value) {
                            AlertDialog(
                                onDismissRequest = { showChangelog.value = false },
                                containerColor = MaterialTheme.colorScheme.surface,
                                titleContentColor = MaterialTheme.colorScheme.onSurface,
                                textContentColor = MaterialTheme.colorScheme.onSurface,
                                title = { Text(stringResource(R.string.changelog), fontWeight = FontWeight.Bold) },
                                text = {
                                    val changelogText = remember {
                                        ChangelogUtils.getChangelog(context)
                                    }
                                    Column(
                                        modifier = Modifier
                                            .verticalScroll(rememberScrollState())
                                            .padding(horizontal = 12.dp, vertical = 8.dp)
                                    ) {
                                        Text(
                                            text = changelogText,
                                            color = MaterialTheme.colorScheme.onSurface,
                                            fontSize = 14.sp,
                                            lineHeight = 20.sp
                                        )
                                    }
                                },
                                confirmButton = {
                                    TextButton(onClick = { showChangelog.value = false }) {
                                        Text(stringResource(R.string.ok), color = MaterialTheme.colorScheme.onSurface)
                                    }
                                }
                            )
                        }
                    }
                }
            },
            confirmButton = {
                if (onConfirm != null) {
                    TextButton(onClick = onConfirm) {
                        Text(stringResource(R.string.ok), color = MaterialTheme.colorScheme.onSurface)
                    }
                } else {
                    TextButton(onClick = onDismiss) {
                        Text(stringResource(R.string.close), color = MaterialTheme.colorScheme.onSurface)
                    }
                }
            }
        )
    }
}
