package com.example.moviesaandseries.presentation.signIn

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.moviesaandseries.R
import com.example.moviesaandseries.presentation.general.ShimmerListItem
import com.example.moviesaandseries.presentation.general.TextSubTitulos
import com.google.android.gms.auth.api.identity.Identity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlin.coroutines.coroutineContext


@Composable
fun ProfileScreen(
    userData: UserData?,
    onSignOut: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp),
        //verticalArrangement = Arrangement.Center,
        //horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = if (userData?.profilePictureUrl != null) userData.profilePictureUrl else R.drawable.logo,
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.width(320.dp))
            Icon(
                //painter = painterResource(id = R.drawable.ic_movie),
                painterResource(id = R.drawable.ic_logout),
                contentDescription = null,
                modifier = Modifier
                    .requiredSize(35.dp)
                    .clickable { onSignOut() }
            )

        }
        Column(
            modifier = Modifier
                .padding(vertical = 16.dp)
        ) {
            TextSubTitulos(title = "Filmes favoritos")
            Spacer(modifier = Modifier.height(5.dp))
            LazyRow(
                modifier = Modifier.padding(
                    horizontal = 5.dp
                )
            ) {
                items(20) {
                    ShimmerListItem(isLoading = true,
                        contentAfterLoading = { /*TODO*/ })
                }
            }
        }
        Column(
            
        ) {
            TextSubTitulos(title = "Séries favoritas")
            Spacer(modifier = Modifier.height(5.dp))
            LazyRow(
                modifier = Modifier.padding(
                    horizontal = 5.dp
                )
            ) {
                items(20) {
                    ShimmerListItem(isLoading = true,
                        contentAfterLoading = { /*TODO*/ })
                }
            }
        }
    }
}

