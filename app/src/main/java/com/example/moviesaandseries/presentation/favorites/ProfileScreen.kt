package com.example.moviesaandseries.presentation.favorites

import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.moviesaandseries.R
import com.example.moviesaandseries.presentation.general.ShimmerListItem
import com.example.moviesaandseries.presentation.general.TextSubTitulos
import com.example.moviesaandseries.presentation.movie_list.MovieListScreenCellFirebase
import com.example.moviesaandseries.presentation.signIn.UserData


@Composable
fun ProfileScreen(
    navController: NavController,
    userData: UserData?,
    onSignOut: () -> Unit,
    viewModel: FavoriteViewModel = hiltViewModel(),
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
            Movies(userData = userData,
                tipo = "movies"
            ) { movies ->
                MovieListScreenCellFirebase(
                    navController = navController,
                    movies = movies,
                    tipo = "movie",
                ) { idFirebase ->
                    viewModel.deleteMovie(idFirebase)
                }
            }
        }

        Column(
            modifier = Modifier
                .padding(vertical = 16.dp)
        ) {
            TextSubTitulos(title = "Séries favoritas")
            Spacer(modifier = Modifier.height(5.dp))
            Movies(userData = userData,
                tipo = "series"
            ) { movies ->
                MovieListScreenCellFirebase(
                    navController = navController,
                    movies = movies,
                    tipo = "series",
                ) { idFirebase ->
                    viewModel.deleteMovie(idFirebase)
                }
            }
        }

        }

    }


