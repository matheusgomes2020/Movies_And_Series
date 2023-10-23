package com.example.moviesaandseries.presentation.newUI

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviesaandseries.R
import com.example.moviesaandseries.ui.theme.MoviesAandSeriesTheme

@Composable
fun MainAppBar(
    modifier: Modifier = Modifier,
    onSearchClick: () -> Unit = {},
    //onNotificationClick: () -> Unit = {},
    onLogoClick: () -> Unit = {},
    isNotificationIconVisible: Boolean = true,
    @DrawableRes icon1: Int,
   // @DrawableRes icon2: Int,
    title: String
) {

    Box(
        modifier = modifier.background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(
                horizontal = DpDimensions.Normal,
                vertical = DpDimensions.Small
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(DpDimensions.Dp30)
                    .clickable { onLogoClick() }
            )

            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                //color = MaterialTheme.colorScheme.inversePrimary,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = DpDimensions.Normal),
                textAlign = TextAlign.Start,
                maxLines = 1
            )


            IconButton(onClick = onSearchClick) {
                Icon(
                    painter = painterResource(id = icon1),
                    contentDescription = null,
                    //tint = MaterialTheme.colorScheme.inversePrimary
                )
            }

//            if (isNotificationIconVisible) {
//                IconButton(onClick = onNotificationClick) {
//                    Icon(
//                        painter = painterResource(id = icon2),
//                        contentDescription = null,
//                        tint = MaterialTheme.colorScheme.inversePrimary
//                    )
//                }
//            }
        }
    }
}

@Composable
fun AppBarWithBackAndIcon(
    modifier: Modifier = Modifier,
    title: String,
    backIcon: ImageVector,
    icon: Int,
    onBackClick: () -> Unit = {},
    onIconClick: () -> Unit = {}
) {
    Box(
        modifier = modifier.background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(
                horizontal = DpDimensions.Smallest,
                vertical = DpDimensions.Small
            )
        ) {

            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = backIcon,
                    contentDescription = null,
                )
            }
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = DpDimensions.Normal),
                textAlign = TextAlign.Start,
            )
            IconButton(onClick = onIconClick) {
                Icon(
                    modifier = Modifier
                        .size(35.dp),
                    painter = painterResource(id = icon),
                    contentDescription = null,
                )
            }
        }
    }
}




@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun LogoAppBarWithTwoActionsPreview() {
    MoviesAandSeriesTheme {
        MainAppBar(
            icon1 = R.drawable.search,
            //icon2 = R.drawable.notification,
            title = stringResource(id = R.string.app_name)
        )
    }
}

object DpDimensions {

    val Dp20 = 20.dp
    val Dp50 = 50.dp
    val Dp40 = 40.dp
    val Dp100 = 100.dp
    val Dp30 = 30.dp
    val Normal = 16.dp
    val Small = 10.dp
    val Smallest = 5.dp
    val Dp24 = 24.dp


}