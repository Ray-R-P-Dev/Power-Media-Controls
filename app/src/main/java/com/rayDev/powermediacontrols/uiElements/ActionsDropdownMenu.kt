package com.rayDev.powermediacontrols.uiElements

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rayDev.powermediacontrols.R
import com.rayDev.powermediacontrols.ui.theme.PowerMediaControlsTheme

@Composable
fun ActionsList(
    feature: String,
    leftIcon: Int,
    onRunAction: () -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    PowerMediaControlsTheme {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
        ) {
            Card(
                colors = CardDefaults.cardColors(
                     containerColor = MaterialTheme.colorScheme.surface
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { expanded = true })
                    .clip(RoundedCornerShape(10.dp))
                    .padding(start = 3.dp, end = 3.dp, top = 2.dp, bottom = 2.dp)

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(leftIcon),
                        contentDescription = stringResource(R.string.action_icon),
                        modifier = Modifier
                            .padding(6.dp)
                            .size(45.dp)
                    )
                    Text(
                        text = feature,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(5.dp)
                    )
                }
            }

            DropdownMenu(
                containerColor = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(16.dp),
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.run_action), color = MaterialTheme.colorScheme.onSurface) },
                    onClick = {
                        onRunAction()
                        expanded = false
                    }
                )
            }
        }
    }
}