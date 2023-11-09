package com.popcine.moviesaandseries.presentation.person_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.popcine.moviesaandseries.common.navigation.AppGraph
import com.popcine.moviesaandseries.data.remote.dto.Profile
import com.popcine.moviesaandseries.presentation.general.CustomPadding
import com.popcine.moviesaandseries.presentation.general.DpDimensions
import com.popcine.moviesaandseries.presentation.general.SubtitleHeader

@Composable
fun PersonImagesCell(
    navController: NavController,
    images: List<Profile>){
    CustomPadding(
        verticalPadding = 0.dp,
        horizontalPadding = DpDimensions.Normal
    ) {
        SubtitleHeader(
            title = "Imagens",
            modifier = Modifier.fillMaxWidth(),
            isSystemInDarkTheme = true,
            isIconVisible = false,
            onClick = {
            }
        )
    }
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small),
            contentPadding = PaddingValues(horizontal = 16.dp),
        ) {
            items(images) { image ->
                ImagePersonListItem(
                    profile = image,
                    modifier = Modifier
                        .fillMaxWidth(),
                    onItemClick = {
                        try {
                            val image_path = image.file_path
                            val stringBuilder = StringBuilder(image_path)
                            stringBuilder.deleteCharAt(0)
                            navController.navigate(AppGraph.image_cast_details.DETAILS + "/${stringBuilder}")
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    })
                Spacer(modifier = Modifier.width(7.dp))
            }
        }

}