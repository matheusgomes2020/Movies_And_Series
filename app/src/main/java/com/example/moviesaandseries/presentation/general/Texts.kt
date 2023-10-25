package com.example.moviesaandseries.presentation.general

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviesaandseries.ui.theme.BlueGrey11
import com.example.moviesaandseries.ui.theme.fontFamily3

@Composable
fun TextSubTitulos(title: String){
    Text(text = title,
        fontSize = 18.sp,
        //style = MaterialTheme.typography.displayMedium,
        fontWeight = FontWeight.Bold,
        fontFamily = fontFamily3,
    )
}

@Composable
fun TextTitulos(title: String){
    Text(text = title,
        fontSize = 20.sp,
        style = MaterialTheme.typography.displayMedium,
        fontWeight = FontWeight.Bold,
        fontFamily = fontFamily3,
        maxLines = 1
    )


}

@Composable
fun TextBiografia(title: String){
    Text(
        text = title,
        //style = MaterialTheme.typography.headlineMedium,
        lineHeight = 25.sp,
        fontSize = 16.sp,
        //fontFamily = fontFamily3
    )
}

@Composable
fun TextCards(title: String){
    Text(
        text = title,
        modifier = Modifier
            .width(110.dp)
            .background( color = if (isSystemInDarkTheme())  BlueGrey11 else Color.White )
            .padding(start = 3.dp),
        fontSize = 13.sp,
        fontFamily = fontFamily3,
        maxLines = 1
    )
}

@Composable
fun TextSearchBar(title: String){
    Text(text = title,
        modifier = Modifier,
        color = Color.LightGray,
        fontSize = 13.sp,
        textAlign = TextAlign.Center
        , fontFamily = fontFamily3
    )
}