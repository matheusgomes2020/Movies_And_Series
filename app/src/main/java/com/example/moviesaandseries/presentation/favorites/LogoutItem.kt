package com.example.moviesaandseries.presentation.favorites

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.moviesaandseries.presentation.general.DpDimensions
import com.example.moviesaandseries.ui.theme.Grey62
import com.example.moviesaandseries.ui.theme.Red70


@Composable
fun LogoutItem(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    title: String,
    onItemClick: () -> Unit
) {

    Surface(
        onClick = { onItemClick() },
        shape = RoundedCornerShape(DpDimensions.Small),
        color = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(
                horizontal = DpDimensions.Small,
                vertical = DpDimensions.Small
            )
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = Color.Red
            )

            Text(
                text = title, style = MaterialTheme.typography.titleMedium,
                color = Color.Red,
                modifier = Modifier
                    .padding(horizontal = DpDimensions.Normal)
                    .weight(1f)
            )
        }

    }

}



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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteMovieBottomSheet(
    name: String,
    modifier: Modifier = Modifier,
    bottomSheetState: SheetState,
    onDismiss: () -> Unit,
    onLogout: (Boolean) -> Unit,
    onCancel: () -> Unit
) {

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        shape = RoundedCornerShape(
            DpDimensions.Dp20
        ),
        sheetState = bottomSheetState,
        modifier = modifier
            .padding(
                DpDimensions.Dp20)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(DpDimensions.Normal)
                .fillMaxWidth(),
        ) {

            Text(text = "Remover favorito",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Red)

            Spacer(modifier = Modifier.height(DpDimensions.Dp40))
            val aText = buildAnnotatedString {
                append("Remover ")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(name)
                }
                append(" dos favoritos?")
            }
            Text(text = aText,
                style = MaterialTheme.typography.bodyMedium,
                //color =MaterialTheme.colorScheme.inversePrimary,
                textAlign = TextAlign.Center)

            Spacer(modifier = Modifier.height(DpDimensions.Dp20))

            TwoButtonsColumn(
                leftButtonText = "cancelar",
                rightButtonText = "sim",
                onLeftButtonClick = { onCancel() },
                onRightButtonClick = { onLogout(true) })

        }
    }

}