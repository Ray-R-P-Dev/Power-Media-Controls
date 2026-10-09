@file:Suppress("DEPRECATION")

package com.rayDev.powermediacontrols.uiElements

import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.rayDev.powermediacontrols.R
import com.rayDev.powermediacontrols.ui.theme.PowerMediaControlsTheme
import com.rayDev.powermediacontrols.screens.MiddleScreen


@Composable
fun TheTopBar() {
    val unknownVersion = stringResource(R.string.unknown)
    val uriHandler = LocalUriHandler.current
    val currentContext = LocalContext.current
    var expanded by remember { mutableStateOf(value = false) }
    var showDocumentationPopup by remember { mutableStateOf(value = false) }

    val appVersion = remember {
        try {
            val packageInfo =
                currentContext.packageManager.getPackageInfo(currentContext.packageName, 0)
            packageInfo.versionName ?: unknownVersion
        } catch (_: Exception) {
            unknownVersion
        }
    }

    val routinesNotAvailableText = stringResource(R.string.samsung_routines_not_available)
    val supportEmailSubject = stringResource(R.string.support_email_subject)
    val supportEmailBody = stringResource(
        R.string.support_email_body,
        Build.VERSION.RELEASE,
        appVersion,
        Build.MODEL
    )
    val noEmailAppFoundText = stringResource(R.string.no_email_app_found)

    PowerMediaControlsTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = Color.White,
                    ),
                    actions = {
                        Box {
                            IconButton(onClick = { expanded = !expanded }) {
                                Icon(
                                    tint = Color.White,
                                    imageVector = Icons.Filled.MoreVert,
                                    contentDescription = null,
                                    modifier = Modifier.size(30.dp),
                                )
                            }
                            DropdownMenu(
                                containerColor = MaterialTheme.colorScheme.surface,
                                shape = RoundedCornerShape(16.dp),
                                expanded = expanded,
                                onDismissRequest = { expanded = false },
                            ) {
                                DropdownMenuItem(
                                    text = { Text(stringResource(R.string.open_routines), color = if (isSystemInDarkTheme()) Color.White else Color.Black) },
                                    leadingIcon = {
                                        Icon(
                                            painter = painterResource(R.drawable.circle_dashed_check),
                                            tint = if (isSystemInDarkTheme()) Color.White else Color.Black,
                                            contentDescription = null,
                                            modifier = Modifier.size(28.dp),

                                        )
                                    },
                                    trailingIcon = {
                                        Icon(
                                            painter = painterResource(R.drawable.external_link),
                                            tint = if (isSystemInDarkTheme()) Color.White else Color.Black,
                                            contentDescription = null,
                                            modifier = Modifier.size(22.dp),
                                        )
                                    },
                                    onClick = {
                                        expanded = false
                                        val packageName = "com.samsung.android.app.routines"

                                        val launchIntent =
                                            currentContext.packageManager.getLaunchIntentForPackage(
                                                packageName
                                            )

                                        if (launchIntent != null) {

                                            currentContext.startActivity(launchIntent)
                                        } else {

                                            Toast.makeText(
                                                currentContext,
                                                routinesNotAvailableText,
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }


                                    }
                                )
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            stringResource(R.string.more_modules),
                                            color = if (isSystemInDarkTheme()) Color.White else Color.Black
                                        )
                                    },
                                    leadingIcon = {
                                        Icon(
                                            painter = painterResource(R.drawable.world),
                                            tint = if (isSystemInDarkTheme()) Color.White else Color.Black,
                                            contentDescription = null,
                                            modifier = Modifier.size(28.dp),

                                        )
                                    },
                                    trailingIcon = {
                                        Icon(
                                            painter = painterResource(R.drawable.external_link),
                                            tint = if (isSystemInDarkTheme()) Color.White else Color.Black,
                                            contentDescription = null,
                                            modifier = Modifier.size(22.dp),
                                        )
                                    },
                                    onClick = {
                                        expanded = false
                                        uriHandler.openUri("https://github.com/Ray-R-P-Dev?tab=repositories")


                                    }
                                )
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            stringResource(R.string.support),
                                            color = if (isSystemInDarkTheme()) Color.White else Color.Black
                                        )
                                    },
                                    leadingIcon = {
                                        Icon(
                                            painter = painterResource(R.drawable.mail_share),
                                            tint = if (isSystemInDarkTheme()) Color.White else Color.Black,
                                            contentDescription = null,
                                            modifier = Modifier.size(28.dp),

                                        )
                                    },
                                    trailingIcon = {
                                        Icon(
                                            painter = painterResource(R.drawable.external_link),
                                            tint = if (isSystemInDarkTheme()) Color.White else Color.Black,
                                            contentDescription = null,
                                            modifier = Modifier.size(22.dp),
                                        )
                                    },
                                    onClick = {
                                        expanded = false
                                        val eMailIntent = Intent(Intent.ACTION_SENDTO).apply {
                                            data = "mailto:".toUri()
                                            putExtra(
                                                Intent.EXTRA_EMAIL,
                                                arrayOf("raymailadd@gmail.com")
                                            )
                                            putExtra(
                                                Intent.EXTRA_SUBJECT,
                                                supportEmailSubject
                                            )
                                            putExtra(
                                                Intent.EXTRA_TEXT,
                                                supportEmailBody
                                            )
                                        }
                                        if (eMailIntent.resolveActivity(currentContext.packageManager) != null) {
                                            currentContext.startActivity(eMailIntent)
                                        } else {
                                            Toast.makeText(
                                                currentContext,
                                                noEmailAppFoundText,
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                )
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            stringResource(R.string.donate),
                                            color = if (isSystemInDarkTheme()) Color.White else Color.Black
                                        )
                                    },

                                    leadingIcon = {
                                        Icon(
                                            painter = painterResource(R.drawable.tip),
                                            tint = if (isSystemInDarkTheme()) Color.White else Color.Black,
                                            contentDescription = null,
                                            modifier = Modifier.size(28.dp),

                                        )
                                    },

                                    trailingIcon = {
                                        Icon(
                                            painter = painterResource(R.drawable.external_link),
                                            tint = if (isSystemInDarkTheme()) Color.White else Color.Black,
                                            contentDescription = null,
                                            modifier = Modifier.size(22.dp),
                                        )
                                    },

                                    onClick = {
                                        expanded = false
                                        uriHandler.openUri("https://buymeacoffee.com/RayRParker")
                                    }
                                )
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            stringResource(R.string.documentation),
                                            color = if (isSystemInDarkTheme()) Color.White else Color.Black
                                        )
                                    },
                                    leadingIcon = {
                                        Icon(
                                            painter = painterResource(R.drawable.info_circle),
                                            tint = if (isSystemInDarkTheme()) Color.White else Color.Black,
                                            contentDescription = null,
                                            modifier = Modifier.size(28.dp),

                                        )
                                    },

                                    onClick = {
                                        expanded = false
                                        showDocumentationPopup = true
                                    }
                                )
                            }
                        }
                    },
                    title = {
                        Text(
                            stringResource(R.string.app_name),
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                )
            },
        ) { innerPadding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                color = MaterialTheme.colorScheme.background
            )
            {
                MiddleScreen()
                if (showDocumentationPopup) {
                    AppPopup(
                        show = true,
                        onDismiss = { showDocumentationPopup = false },
                        header = stringResource(R.string.info),
                        body = "v$appVersion",
                        showExtraLinks = true
                    )
                }
            }
        }
    }
}
