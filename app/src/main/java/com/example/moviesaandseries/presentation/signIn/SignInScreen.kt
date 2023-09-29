package com.example.moviesaandseries.presentation.signIn

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.moviesaandseries.R

@Composable
fun SignInScreen(
    state: SignInState,
    onSignInClick: () -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column {



        Image(painterResource(id  = R.drawable.logo), contentDescription = "google logo",
            modifier = Modifier.size(100.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Row(
            modifier = Modifier
                .clickable { onSignInClick() }
                .width(100.dp)
                .height(30.dp)
                .clip(shape = RoundedCornerShape(10.dp)),) {
//            Icon(painterResource(id  = R.drawable.googlelogo), contentDescription = "google logo",
//                modifier = Modifier.size(23.dp))
            Image(painterResource(id  = R.drawable.googlelogo), contentDescription = "google logo",
                modifier = Modifier.size(23.dp))
            Text(text = "Login",
                modifier = Modifier
                    .padding( start = 5.dp ))

        }
        }
    }
}