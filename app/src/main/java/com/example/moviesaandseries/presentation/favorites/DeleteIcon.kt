package com.example.moviesaandseries.presentation.favorites

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
            Modifier.size(10.dp)
        )
    }
}