package com.popcine.moviesaandseries.domain.use_case.get_series

data class SeriesUseCases(
    val getAiringToday: GetAiringTodayUseCase,
    val getOnAir: GetOnAirSeriesUseCase,
    val getPopular: GetPopularSeriesUseCase,
    val getRated: GetRatedSeriesUseCase,
)