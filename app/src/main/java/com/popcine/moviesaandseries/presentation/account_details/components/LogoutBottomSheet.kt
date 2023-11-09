package com.popcine.moviesaandseries.presentation.account_details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.popcine.moviesaandseries.presentation.general.DpDimensions
import com.popcine.moviesaandseries.presentation.general.TwoButtonsColumn

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogoutBottomSheet(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = DpDimensions.Dp20,
    bottomSheetState: SheetState,
    onDismiss: () -> Unit,
    onLogout: () -> Unit,
    onCancel: () -> Unit
) {

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        shape = RoundedCornerShape(topStart = cornerRadius, topEnd = cornerRadius),
        sheetState = bottomSheetState,
        modifier = modifier
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(DpDimensions.Normal)
                .fillMaxWidth(),
        ) {

            Text(text = "Sair",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Red)

            Spacer(modifier = Modifier.height(DpDimensions.Dp20))
            Divider(color = MaterialTheme.colorScheme.inverseSurface)
            Spacer(modifier = Modifier.height(DpDimensions.Dp20))

            Text(text = "Você tem certeza que quer sair?",
                style = MaterialTheme.typography.titleMedium,
                color =MaterialTheme.colorScheme.inversePrimary,
                textAlign = TextAlign.Center)

            Spacer(modifier = Modifier.height(DpDimensions.Dp20))

            TwoButtonsColumn(
                leftButtonText = "cancelar",
                rightButtonText = "sair",
                onLeftButtonClick = { onCancel() },
                onRightButtonClick = { onLogout() })

        }
    }

}