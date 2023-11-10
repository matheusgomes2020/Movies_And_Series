package com.popcine.moviesaandseries.presentation.account_details

import android.content.Intent
import android.net.Uri
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.popcine.moviesaandseries.R
import com.popcine.moviesaandseries.presentation.account_details.components.AccountDetails
import com.popcine.moviesaandseries.presentation.account_details.components.AccountItem
import com.popcine.moviesaandseries.presentation.account_details.components.DarkModeItem
import com.popcine.moviesaandseries.presentation.account_details.components.LanguageItem
import com.popcine.moviesaandseries.presentation.account_details.components.LogoutBottomSheet
import com.popcine.moviesaandseries.presentation.account_details.components.AccountLogoutItem
import com.popcine.moviesaandseries.presentation.general.AppBarWithBack
import com.popcine.moviesaandseries.presentation.general.CustomPadding
import com.popcine.moviesaandseries.presentation.general.DpDimensions
import com.popcine.moviesaandseries.presentation.general.UserData
import com.popcine.moviesaandseries.ui.theme.DarkGrey11
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.popcine.moviesaandseries.common.navigation.AppGraph

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
    val context = LocalContext.current

    val webIntentPrivacy: Intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://doc-hosting.flycricket.io/pop-cine-privacy-policy/7016f44d-67a3-40d5-8700-868e80fab0d1/privacy"))
    val webIntentDelete: Intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://popcinepp.blogspot.com/2023/11/exclusao-de-dados-ou-conta.html"))


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
//                AccountItem(
//                    icon = R.drawable.account, title = "Dados",
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    Toast.makeText( context, "Em breve!!!", Toast.LENGTH_SHORT ).show()
//                }
//                LanguageItem(
//                    icon = R.drawable.language,
//                    title = "Idioma",
//                    language = "Português (BR)",
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    Toast.makeText( context, "Em breve!!!", Toast.LENGTH_SHORT ).show()
//                }
//
//                DarkModeItem(
//                    icon = R.drawable.dark_mode,
//                    title = "Modo escuro",
//                    //onState = {  },
//                    //onCheckChange = viewModel::onCheckChange
//                )

                AccountItem(
                    icon = R.drawable.privacy,
                    title = "Política de privacidade",
                    modifier = Modifier.fillMaxWidth()
                ) {
                    context.startActivity(webIntentPrivacy)                }
//
//                AccountItem(
//                    icon = R.drawable.info,
//                    title = "Sobre",
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    Toast.makeText( context, "Em breve!!!", Toast.LENGTH_SHORT ).show()
//                }
                AccountItem(
                    icon = R.drawable.delete,
                    title = "Excluir conta",
                    modifier = Modifier.fillMaxWidth()
                ) {
                    context.startActivity(webIntentDelete)
                }
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
                       onSignOut().let {
                           navController.navigate(AppGraph.auth.LOGIN)
                       }
            },
            onCancel = {
                isLogoutSheetOpen = false
            }
        )
    }
}