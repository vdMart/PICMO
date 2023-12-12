package com.example.gestordepeliculas.data.repository

import com.example.gestordepeliculas.data.remote.api.ApiService
import com.example.gestordepeliculas.data.remote.models.CountryProviderResults
import com.example.gestordepeliculas.data.remote.models.movie.MovieDetails
import com.example.gestordepeliculas.data.remote.models.movie.MovieResult
import com.example.gestordepeliculas.data.remote.models.movie.TrailerMovieResult
import com.example.gestordepeliculas.ui.state.ResultVM
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getMovieById(idMovie: Int): ResultVM<MovieDetails> {
        return apiService.getMovieById(idMovie)
    }

    suspend fun findMovies(query: String): ResultVM<List<MovieResult>> {
        return apiService.findMovies(query)
    }

    suspend fun getMoviesDiscover(): ResultVM<List<MovieResult>> {
        return apiService.getMoviesDiscover()
    }

    suspend fun getProvidersByIDMovie(id_movie: Int): ResultVM<CountryProviderResults> {
        return apiService.getProvidersByIDMovie(id_movie)
    }

    suspend fun getMovieTrailer(id_movie: Int): ResultVM<List<TrailerMovieResult>> {
        return apiService.getMovieTrailer(id_movie)
    }

}