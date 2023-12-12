package com.example.gestordepeliculas.ui.state.movie

sealed class MovieSerieEvent {
    object GetListMovie: MovieSerieEvent()
    data class GetFindMovie(val query: String): MovieSerieEvent()
    object GetListSerie: MovieSerieEvent()
    data class GetFindSerie(val query: String): MovieSerieEvent()
    object GetListPerson: MovieSerieEvent()
    data class GetFindPerson(val query: String): MovieSerieEvent()
}