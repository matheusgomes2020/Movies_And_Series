package com.example.moviesaandseries.presentation.account_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviesaandseries.R
import com.example.moviesaandseries.presentation.account_details.components.AccountDetails
import com.example.moviesaandseries.presentation.account_details.components.AccountItem
import com.example.moviesaandseries.presentation.account_details.components.DarkModeItem
import com.example.moviesaandseries.presentation.account_details.components.LanguageItem
import com.example.moviesaandseries.presentation.account_details.components.LogoutBottomSheet
import com.example.moviesaandseries.presentation.account_details.components.AccountLogoutItem
import com.example.moviesaandseries.presentation.general.AppBarWithBack
import com.example.moviesaandseries.presentation.general.CustomPadding
import com.example.moviesaandseries.presentation.general.DpDimensions
import com.example.moviesaandseries.presentation.general.UserData
import com.example.moviesaandseries.ui.theme.DarkGrey11
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountDetailsScreen(
    navController: NavController,
    isSystemInDarkTheme: Boolean,
    userData: UserData?,
    onSignOut: () -> Unit,
) {
    val systemUiController = rememberSystemUiController()
    val bottomSheetState = rememberModalBottomSheetState()
    val useDarkIcons = !isSystemInDarkTheme
    val scrollState = rememberScrollState()
    var isLogoutSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }



    SideEffect {
        systemUiController.setSystemBarsColor(
            color = if (useDarkIcons)
                Color.White else DarkGrey11,
            darkIcons = useDarkIcons
        )
    }

    Scaffold(
        topBar = {
            AppBarWithBack(
                title = "Minha conta", backIcon = Icons.Default.ArrowBack, onBackClick = {
                    navController.popBackStack()
                })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .fillMaxSize()
                .background(
                    color = if (useDarkIcons)
                        Color.White else DarkGrey11
                )
        ) {
            AccountDetails(navController = navController, userData = userData)
            Spacer(modifier = Modifier.height(DpDimensions.Small))

            CustomPadding(
                verticalPadding = 0.dp,
                horizontalPadding = DpDimensions.Normal
            ) {
                AccountItem(
                    icon = R.drawable.account, title = "Dados",
                    modifier = Modifier.fillMaxWidth()
                ) {
                }
                LanguageItem(
                    icon = R.drawable.language,
                    title = "Idioma",
                    language = "Português (BR)",
                    modifier = Modifier.fillMaxWidth()
                ) {
                }

                DarkModeItem(
                    icon = R.drawable.dark_mode,
                    title = "Modo escuro",
                    //onState = {  },
                    //onCheckChange = viewModel::onCheckChange
                )

                AccountItem(
                    icon = R.drawable.privacy,
                    title = "Política de privacidade",
                    modifier = Modifier.fillMaxWidth()
                ) { }

                AccountItem(
                    icon = R.drawable.info,
                    title = "Sobre",
                    modifier = Modifier.fillMaxWidth()
                ) { }
                AccountItem(
                    icon = R.drawable.delete,
                    title = "Excluir conta",
                    modifier = Modifier.fillMaxWidth()
                ) { }
                AccountLogoutItem(
                    icon = R.drawable.logout,
                    title = "Sair",
                    modifier = Modifier.fillMaxWidth()
                ) {
                    isLogoutSheetOpen = true
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
    if (isLogoutSheetOpen) {
        LogoutBottomSheet(
            bottomSheetState = bottomSheetState,
            onDismiss = { isLogoutSheetOpen = false },
            onLogout = {
                       onSignOut()
            },
            onCancel = {
                isLogoutSheetOpen = false
            }
        )
    }
}