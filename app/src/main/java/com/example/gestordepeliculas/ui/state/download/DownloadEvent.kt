package com.example.gestordepeliculas.ui.state.download

import com.example.gestordepeliculas.ui.state.home.HomeEvent

sealed class DownloadEvent {
    data class GetMoviesById(val ids: List<Int>): DownloadEvent()
    data class GetSeriesById(val ids: List<Int>): DownloadEvent()
}