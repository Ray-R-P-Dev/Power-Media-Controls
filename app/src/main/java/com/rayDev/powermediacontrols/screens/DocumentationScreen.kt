package com.rayDev.powermediacontrols.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rayDev.powermediacontrols.R
import com.rayDev.powermediacontrols.ui.theme.PowerMediaControlsTheme

class DocumentationScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PowerMediaControlsTheme {
                DocumentationView(onBack = { finish() })
            }
        }
    }
}

@Composable
fun DocSection(title: String, body: String) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = body,
            style = MaterialTheme.typography.bodyLarge,
            lineHeight = 22.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun DocumentationView(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.doc_title),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            painter = painterResource(R.drawable.arrow_left),
                            contentDescription = stringResource(R.string.back),
                            modifier = Modifier.size(30.dp),
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    DocSection(
                        title = stringResource(R.string.doc_intro_title),
                        body = stringResource(R.string.doc_intro_body)
                    )
                }

                item { HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp) }

                item {
                    DocSection(
                        title = stringResource(R.string.doc_how_to_title),
                        body = stringResource(R.string.doc_how_to_body)
                    )
                }

                item {
                    Image(
                        painter = painterResource(id = R.drawable.screenshot_20260823_145444_modes_and_routines),
                        contentDescription = "description of how to use the app",
                        modifier = Modifier
                            .padding(8.dp)
                            .aspectRatio(16f / 9f)
                    )
                }

                item {
                    Text(
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                        text = stringResource(R.string.doc_how_to_body2),
                        modifier = Modifier.padding(16.dp))
                }

                item {
                    Image(
                        painter = painterResource(id = R.drawable.screenshot_20260823_145449_modes_and_routines),
                        contentDescription = "description of how to use the app",
                        modifier = Modifier
                            .padding(horizontal = 30.dp, vertical = 8.dp)

                    )
                }

                item {
                    Text(
                        style = MaterialTheme.typography.bodyLarge,
                        lineHeight = 22.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        text = stringResource(R.string.doc_how_to_body3),
                        modifier = Modifier.padding(16.dp))
                }

                item {
                    Image(
                        painter = painterResource(id = R.drawable.screenshot_20260823_145513_modes_and_routines),
                        contentDescription = "description of how to use the app",
                        modifier = Modifier
                            .padding(8.dp)
                            .aspectRatio(16f / 9f)
                    )
                }

                item { HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp) }

                item {
                    DocSection(
                        title = stringResource(R.string.doc_mechanism_title),
                        body = stringResource(R.string.doc_mechanism_body)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DocumentationPreview() {
    PowerMediaControlsTheme {
        DocumentationView(onBack = {})
    }
}
