package com.example.moviesaandseries.presentation.newUI

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviesaandseries.R
import com.example.moviesaandseries.ui.theme.MoviesAandSeriesTheme

@Composable
fun SubtitleHeader(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit = {}
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            //color = MaterialTheme.colorScheme.inversePrimary,
            modifier = Modifier.weight(1f)
        )


        IconButton(onClick = onClick) {
            Icon(
                painter = painterResource(id = R.drawable.right_arrow),
                contentDescription = "Right arrow",
                //tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(DpDimensions.Dp20)

            )
        }

    }

}


@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun CategoryHeaderPreview() {
    MoviesAandSeriesTheme {
        SubtitleHeader(title = "Em alta", modifier = Modifier.fillMaxWidth())
    }
}