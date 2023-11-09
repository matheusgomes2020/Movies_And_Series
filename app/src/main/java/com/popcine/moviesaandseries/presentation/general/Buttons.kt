package com.popcine.moviesaandseries.presentation.general

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.popcine.moviesaandseries.ui.theme.Grey62
import com.popcine.moviesaandseries.ui.theme.Red70



@Composable
fun TwoButtons(
    leftButtonText: String,
    rightButtonText: String,
    onLeftButtonClick:() -> Unit,
    onRightButtonClick:() -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(DpDimensions.Small))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = 20.dp
                )
        ) {
            OutlinedButton(
                onClick = { onLeftButtonClick() },
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Grey62
                ),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.onPrimary)
            ) {
                Text(
                    text = leftButtonText,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
            Button(
                onClick = {
                    onRightButtonClick()
                },
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Red70
                )
            ) {
                Text(
                    text = rightButtonText,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        }
    }
}

@Composable
fun TwoButtonsColumn(
    leftButtonText: String,
    rightButtonText: String,
    onLeftButtonClick:() -> Unit,
    onRightButtonClick:() -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(DpDimensions.Small))
        Column(

            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = 20.dp
                )
        ) {
            Button(
                onClick = {
                    onRightButtonClick()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Red70
                ),
                shape = RoundedCornerShape(DpDimensions.Dp20)
            ) {
                Text(
                    text = rightButtonText,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
            OutlinedButton(
                onClick = { onLeftButtonClick() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Grey62
                ),
                shape = RoundedCornerShape(DpDimensions.Dp20),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.onPrimary)
            ) {
                Text(
                    text = leftButtonText,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        }
    }
}

