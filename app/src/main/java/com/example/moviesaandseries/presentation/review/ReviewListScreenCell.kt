package com.example.moviesaandseries.presentation.review

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.moviesaandseries.data.remote.dto.Review

@Composable
fun ReviewListScreenCell(
    reviews: List<Review>
) {
    Box(
    ) {
        LazyRow( contentPadding = PaddingValues()){
            items(reviews) { review ->
                ReviewListItem(review
                )
            }
        }
    }
}