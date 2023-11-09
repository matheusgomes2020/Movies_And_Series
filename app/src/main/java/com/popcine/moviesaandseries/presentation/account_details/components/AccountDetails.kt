package com.popcine.moviesaandseries.presentation.account_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.popcine.moviesaandseries.R
import com.popcine.moviesaandseries.presentation.general.UserData
import com.popcine.moviesaandseries.ui.theme.DarkGrey11

@Composable
fun AccountDetails(
    navController: NavController,
    userData: UserData?,
){

    Surface() {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                //.padding(
//                start = 16.dp,
//                end = 16.dp,
//                top = 10.dp,
//                bottom = 10.dp
//            )
                .background(
                    color = if (isSystemInDarkTheme())
                        DarkGrey11 else Color.White)
        ) {
            AsyncImage(
                model =
                //if (userData?.profilePictureUrl != null) userData.profilePictureUrl else
                    R.drawable.padrao,
                contentDescription = "Profile picture",
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(60.dp)
                    .clip(CircleShape)
                    .clickable {

                    },
                contentScale = ContentScale.Crop,
            )


            Column(
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp)
                    .weight(1f)
            ) {
                Text(
                    text = userData?.username!!,
                    style = MaterialTheme.typography.titleMedium,
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = userData?.email!!,
                    style = MaterialTheme.typography.bodySmall,
                    //color = MaterialTheme.colorScheme.onTertiary
                )
            }
        }
    }
}