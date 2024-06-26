package com.example.moviesaandseries.presentation.home
import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.moviesaandseries.common.navigation.HomeNavGraph
import com.example.moviesaandseries.common.navigation.navigationScreens
import com.example.moviesaandseries.presentation.searchMovies.SearchMoviesViewModel
import com.example.moviesaandseries.presentation.general.UserData
import com.example.moviesaandseries.ui.theme.BlueGrey11
import com.example.moviesaandseries.ui.theme.MoviesAandSeriesTheme
import com.example.moviesaandseries.ui.theme.Purple40
import com.example.moviesaandseries.ui.theme.fontFamily3
import com.example.moviesaandseries.ui.theme.teste

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController = rememberNavController(),
               viewModel: SearchMoviesViewModel = hiltViewModel(),
               userData: UserData?,
               onSignOut: () -> Unit) {

    var bottomBarVisible by rememberSaveable {
        mutableStateOf(false)
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()


    bottomBarVisible = when (navBackStackEntry?.destination?.route) {
        Screen.Movies.route -> true
        Screen.Series.route -> true
        Screen.Favorites.route -> true
        else -> false
    }

    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
       // bottomBar = { BottomNavBar(navController = navController) }
    ) {

                HomeNavGraph(navController = navController, userData = userData, onSignOut = onSignOut)
    }
}
@Composable
fun BottomNavBar(navController: NavController, visible: Boolean = true ){
    AnimatedVisibility(visible = visible,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {
        NavigationBar(
            containerColor = if (isSystemInDarkTheme())  Color.Black else Color.White,
           // modifier = Modifier.padding(start = 10.dp, end = 10.dp)
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            navigationScreens.forEach { screen ->
                NavigationBarItem(
                    selected = currentDestination?.hierarchy?.any {
                    it.route == screen.route
                } == true,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }

                            launchSingleTop = true
                            restoreState = true
                        }
                              },
                    icon = {
                        Icon(
                            painterResource(id = screen.icon),
                            contentDescription = "Navigation Icon"
                        )
                    },
                    label = {
                        Text(text = stringResource(id = screen.label)
                        )
                            //, style = MaterialTheme.typography.displaySmall)
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Magenta,
                        indicatorColor = teste,
                        selectedTextColor = if (isSystemInDarkTheme()) Color.Red else Color.Blue
                    ))
            }
        }
    }
}

@Preview
@Composable
fun BottomNavBarPreview() {
    MoviesAandSeriesTheme {
        BottomNavBar(navController = rememberNavController())
        BottomBar(navController = rememberNavController())
    }
}

@Preview
@Composable
fun BottomNavBar2Preview() {
    MoviesAandSeriesTheme {
        BottomBar(navController = rememberNavController())
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Movies,
        BottomBarScreen.Series,
        BottomBarScreen.Favorites,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        BottomNavigation(
            backgroundColor = Color.White
        ) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}


@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        modifier = Modifier
            .background(color = if (isSystemInDarkTheme())  BlueGrey11 else Color.White),
        label = {
            Text(text = screen.label,
                //, color = ( if (isSystemInDarkTheme())    Color.White else BlueGrey11),
                fontFamily = fontFamily3
            )
        },

        icon = {
            Icon(
                //painter = painterResource(id = R.drawable.ic_movie),
                painterResource(id = screen.icon),
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = if (isSystemInDarkTheme()) Color.White else BlueGrey11,
        selectedContentColor = Purple40,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },

    )
}