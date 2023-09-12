package com.example.moviesaandseries.presentation.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun LoginContent(
    onLoginClick: () -> Unit,
    onSignInClick: () -> Unit,
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
            //fontSize = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Medium
        )
        Text(
            modifier = Modifier.clickable { onSignInClick() },
            text = "Sign Up",
            //fontSize = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Medium
        )
        Text(
            modifier = Modifier.clickable { onForgotClick() },
            text = "Forgot Password?",
            //fontSize = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Medium
        )
    }
}