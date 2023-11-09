package com.popcine.moviesaandseries

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.popcine.moviesaandseries.domain.repository.GoogleUiClient
import com.popcine.moviesaandseries.ui.theme.MoviesAandSeriesTheme
import com.google.android.gms.auth.api.identity.Identity
import com.popcine.moviesaandseries.common.navigation.RootNavigationGraph
import com.popcine.moviesaandseries.presentation.signIn.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val authViewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesAandSeriesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RootNavigationGraph(navController = rememberNavController(), viewModel = authViewModel )
                }
            }
        }
    }
}

