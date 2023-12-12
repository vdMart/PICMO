package com.example.gestordepeliculas.ui.state.home

import com.example.gestordepeliculas.domain.model.HomeCategory

sealed class HomeEvent {
    data class GetMoviesById(val ids: List<Int>, val homeCategory: HomeCategory): HomeEvent()
    data class GetSeriesById(val ids: List<Int>, val homeCategory: HomeCategory): HomeEvent()
    data class GetPersonsById(val ids: List<Int>, val homeCategory: HomeCategory): HomeEvent()
    object GetListMoviesDiscover: HomeEvent()
    object GetListSeriesDiscover: HomeEvent()
    object GetListPersonPopular: HomeEvent()
}
