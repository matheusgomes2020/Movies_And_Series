package com.example.moviesaandseries.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.moviesaandseries.R
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Home(
    //navController: NavController
) {
    Scaffold(
        bottomBar = { BottomNav()}
    ) { padding ->
        Text(text = "Olá",
            modifier = Modifier.padding(padding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
) {
    Text(text = "Olá Mundo")
}

@Composable
fun SeriesContent(
) {
    Text(text = "Olá Séries")
}
@Preview
@Composable
fun BottomNav(
    modifier: Modifier = Modifier,
    onMoviesClick: () -> Unit = {},
    onSeriesClick: () -> Unit = {},
    onFavoritesClick: () -> Unit = {}
){
    
     BottomNavigation(
         modifier = modifier.background(Color.Blue)
     ) {
         NavigationBarItem(selected = true, onClick = { onMoviesClick }, icon = {
             Icon(painter = painterResource(id = R.drawable.ic_movie) , contentDescription = null )
         }, label = { Text(text = "Filmes") })
         NavigationBarItem(selected = true, onClick = { onMoviesClick }, icon = {
             Icon(painter = painterResource(id = R.drawable.ic_tv) , contentDescription = null )
         }, label = { Text(text = "Séries") })
         NavigationBarItem(selected = true, onClick = { onMoviesClick }, icon = {
             Icon(painter = painterResource(id = R.drawable.ic_boomark) , contentDescription = null )
         }, label = { Text(text = "Favoritos") })
     }
}