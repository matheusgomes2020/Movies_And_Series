package com.example.moviesaandseries.presentation.general

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun ScreenContent(name: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.clickable { onClick() },
            text = name,
            fontSize = MaterialTheme.typography.h3.fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ScreenContentSeries(
    name: String,
    onClick: () -> Unit,
    onClick2: () -> Unit,
    onClick3: () -> Unit,
    ) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                text = name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                modifier = Modifier.clickable { onClick() },
                text = "Cast List",
                fontSize = 18.sp
            )

            Text(
                modifier = Modifier.clickable { onClick2() },
                text = "Similar List",
                fontSize = 18.sp
            )

            Text(
                modifier = Modifier.clickable { onClick3() },
                text = "Season List",
                fontSize = 18.sp
            )


        }
    }
}

@Composable
fun ScreenContentSeason(
    name: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                text = name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                modifier = Modifier.clickable { onClick() },
                text = "Episodes",
                fontSize = 18.sp
            )

        }
    }
}