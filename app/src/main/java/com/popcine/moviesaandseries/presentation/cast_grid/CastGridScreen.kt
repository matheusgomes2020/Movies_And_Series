package com.popcine.moviesaandseries.presentation.cast_grid

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.popcine.moviesaandseries.common.navigation.AppGraph
import com.popcine.moviesaandseries.presentation.general.AppBarWithBack
import com.popcine.moviesaandseries.presentation.general.DpDimensions
import com.popcine.moviesaandseries.presentation.general.PersonListItem
import com.popcine.moviesaandseries.ui.theme.DarkGrey11


@Composable
fun CastGridScreen(
    navController: NavController,
    viewModel: CastGridViewModel,
) {

    val cast = viewModel.cast

    LaunchedEffect(key1 = cast ) {
        if (cast!=null) {
            Log.d("Será?", "IHH: $cast")
        } else {
            Log.d("Será?", "IHH: $cast")
        }
    }


    Scaffold(
        topBar = {
            AppBarWithBack(title = "Elenco", backIcon = Icons.Default.ArrowBack, onBackClick = {
                navController.popBackStack()
            })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                //.verticalScroll(rememberScrollState())
                .fillMaxSize()
                .background(
                    color = if (isSystemInDarkTheme())
                        DarkGrey11 else Color.White
                )
        ) {

            if (cast!=null) {
                cast.let {

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize(),

                        horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small),
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(DpDimensions.Small)
                    ) {

                        items(cast!!) { cast ->
                            PersonListItem(cast = cast, onItemClick = {
                                navController.navigate(AppGraph.cast_details.DETAILS + "/${cast.id}")
                            },
                                height = 240.dp)
                        }
                    }

                }
            } else {
                Text(text = "erro")
            }
        }
    }

}
