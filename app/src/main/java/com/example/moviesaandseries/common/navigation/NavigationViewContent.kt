package com.example.moviesaandseries.common.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.moviesaandseries.presentation.BottomBar
import com.example.moviesaandseries.ui.theme.MoviesAandSeriesTheme

//import com.example.moviesaandseries.presentation.BottomBar

/*
@Composable
fun ViewContent(name: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.clickable { onClick() },
            text = name,
            //fontSize = ArsenalThemeExtended.typography.h1.fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
fun ViewContentPreview() {
    MoviesAandSeriesTheme  {
        //ViewContent("Conteúdo que exibirei!") {}
    }
}
@Preview
@Composable
fun HomeViewContentPreview() {
    MoviesAandSeriesTheme {
        HomeViewContent(rememberNavController())
    }
}

@Composable
fun LoginContent(
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onForgotClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.clickable { onLoginClick() },
            text = "Login",
            //fontSize = ArsenalThemeExtended.typography.h1.fontSize,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier.clickable { onSignUpClick() },
            text = "Sign Up",
            //fontSize = ArsenalThemeExtended.typography.h1.fontSize,
            fontWeight = FontWeight.Medium
        )
        Text(
            modifier = Modifier.clickable { onForgotClick() },
            text = "Forgot Password",
           // fontSize = ArsenalThemeExtended.typography.h1.fontSize,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview
@Composable
fun LoginContentPreview() {
    MoviesAandSeriesTheme  {
        LoginContent({},{},{})
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeViewContent(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        HomeNavGraph(navController = navController)
    }
}

 */