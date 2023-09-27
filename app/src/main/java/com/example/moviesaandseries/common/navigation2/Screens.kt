package com.example.moviesaandseries.common.navigation2

enum class Screens {
    LoginScreen,
    SignInScreen,
    SplashScreen,
    ForgotScreen,
    HomeScreen,
    MoviesScreen,
    SeriesScreen,
    FavoritesScreen,
    MovieDetailsScreen,
    SeriesDetailsScreen,
    SearchMoviesScreen,
    SearchSeriesScreen,
    CastScreen,
    EpisodeScreen,
    SeasonScreen,
    ImageCastScreen;

    companion object {
        fun fromRoute( route: String? ): Screens
        = when( route?.substringBefore("/") ) {
            LoginScreen.name -> LoginScreen
            SignInScreen.name -> SignInScreen
            SplashScreen.name -> SplashScreen
            ForgotScreen.name -> ForgotScreen
            HomeScreen.name -> HomeScreen
            SeriesScreen.name -> SeriesScreen
            FavoritesScreen.name -> FavoritesScreen
            MovieDetailsScreen.name -> MovieDetailsScreen
            SeriesDetailsScreen.name -> SeriesDetailsScreen
            SearchMoviesScreen.name -> SearchMoviesScreen
            SearchSeriesScreen.name -> SearchSeriesScreen
            CastScreen.name -> CastScreen
            EpisodeScreen.name -> EpisodeScreen
            SeasonScreen.name -> SeasonScreen
            ImageCastScreen.name -> ImageCastScreen
            null -> MoviesScreen
            else -> throw  IllegalArgumentException("Route $route is not recognized")
        }
    }

}
