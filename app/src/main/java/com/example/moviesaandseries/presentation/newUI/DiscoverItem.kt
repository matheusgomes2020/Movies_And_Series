package com.example.moviesaandseries.presentation.newUI

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviesaandseries.ui.theme.MoviesAandSeriesTheme

@Composable
fun DiscoverItem(
    modifier: Modifier = Modifier,
    discover: Discover,
    onClick: (Discover) -> Unit = {}
) {

    Surface(
        onClick = { onClick(discover) },
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outline),
        shape = RoundedCornerShape(DpDimensions.Small),
        modifier = Modifier.width(200.dp)
    ) {

        Column {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(
                        discover.backgroundColor,
                        RoundedCornerShape(
                            topStart = DpDimensions.Small,
                            topEnd = DpDimensions.Small
                        )
                    )
                    .fillMaxWidth()
                    .height(120.dp)
                    .paint(
                        painter = painterResource(id = discover.image),
                        contentScale = ContentScale.FillBounds
                    )
            ) {
//                Box(modifier = Modifier.padding(DpDimensions.Dp20)) {
//                    Image(
//                        painter = painterResource(id = discover.image), contentDescription = null,
//                        modifier = Modifier.size(70.dp)
//                    )
//                }

            }

            Column(
                modifier = Modifier.padding(
                    vertical = DpDimensions.Normal,
                    horizontal = DpDimensions.Normal
                )
            ) {

                Text(
                    text = discover.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.inversePrimary
                )

                Spacer(modifier = Modifier.height(DpDimensions.Small))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = discover.userImage),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(DpDimensions.Dp30),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = discover.user,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSecondary,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = DpDimensions.Small)
                    )

                }

            }
        }

    }

}

@Preview
@Composable
fun DiscoverItemPreview() {
    MoviesAandSeriesTheme {
        DiscoverItem(discover = discovers[0])
    }
}