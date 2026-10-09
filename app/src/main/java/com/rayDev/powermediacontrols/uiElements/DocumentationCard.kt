package com.rayDev.powermediacontrols.uiElements

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rayDev.powermediacontrols.R
import com.rayDev.powermediacontrols.screens.DocumentationScreen

@Composable
@Preview(showBackground = true)
fun InfoCard() {
    val context = LocalContext.current

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .clickable(onClick = {
                Intent(context, DocumentationScreen::class.java).also {
                    context.startActivity(it)
                }
            })
    )
    {
        Column {
            Row(
                horizontalArrangement = Arrangement.Absolute.Left,
                modifier = Modifier
                    .padding(12.dp)


            ) {
                Icon(
                    painter = painterResource(R.drawable.file_info),
                    tint = Color.White,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(4.dp)
                        .size(28.dp)


                )
                Text(
                    text = stringResource(R.string.how_to_use_this_app),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(start = 4.dp, bottom = 7.dp, top = 4.dp)
                )
            }
            Text(
                text = stringResource(R.string.check_the_documentation),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(start = 18.dp, bottom = 20.dp)
            )
        }
    }
}