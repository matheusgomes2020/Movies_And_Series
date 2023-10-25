package com.example.moviesaandseries.presentation.general

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle

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