package com.example.moviesaandseries.presentation.favorites

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable

@Composable
fun DeleteIcon(
    deleteMovie: () -> Unit
) {
    IconButton(
        onClick = deleteMovie
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "",
        )
    }
}