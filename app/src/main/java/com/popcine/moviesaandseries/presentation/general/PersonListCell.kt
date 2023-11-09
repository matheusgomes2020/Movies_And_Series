package com.popcine.moviesaandseries.presentation.general

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.popcine.moviesaandseries.common.navigation.AppGraph
import com.popcine.moviesaandseries.data.remote.dto.movies.Cast

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
            PersonListItem(
                cast = cast,
                onItemClick = {
                    try {
                        navController.navigate(AppGraph.cast_details.DETAILS + "/${cast.id}")
                    }catch (e: Exception){
                        Log.d("QQQ", "CastCell: ${e.printStackTrace()}")
                    }
                },
                height = 140.dp
            )
        }
    }
}