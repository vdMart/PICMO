package com.example.gestordepeliculas.ui.state.details

sealed class DetailsEvent {
    data class GetMovieById(val id: Int): DetailsEvent()
    data class GetSerieById(val id: Int): DetailsEvent()
    data class GetPersonById(val id: Int): DetailsEvent()
    data class GetMovieProviderById(val id: Int): DetailsEvent()
    data class GetSerieProviderById(val id: Int): DetailsEvent()
}