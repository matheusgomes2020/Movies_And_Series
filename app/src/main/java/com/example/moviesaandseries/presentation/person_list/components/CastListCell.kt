package com.example.moviesaandseries.presentation.person_list.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviesaandseries.common.navigation.AppGraph
import com.example.moviesaandseries.data.remote.dto.movies.Cast
import com.example.moviesaandseries.presentation.general.DpDimensions

@Composable
fun CastListCell(
    navController: NavController,
    cast: List<Cast>,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small),
        contentPadding = PaddingValues(horizontal = 16.dp),
    ) {
        items(cast) { cast ->
            PersonListItemNewUI(
                cast = cast,
                onItemClick = {
                    try {
                        navController.navigate(AppGraph.cast_details.DETAILS + "/${cast.id}")
                    }catch (e: Exception){
                        Log.d("QQQ", "CastCell: ${e.printStackTrace()}")
                    }
                }
            )
        }
    }
}