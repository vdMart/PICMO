package com.example.gestordepeliculas.domain.usecase

import com.example.gestordepeliculas.data.remote.models.CountryProviderResults
import com.example.gestordepeliculas.data.remote.models.movie.MovieDetails
import com.example.gestordepeliculas.data.remote.models.movie.MovieResult
import com.example.gestordepeliculas.data.remote.models.movie.TrailerMovieResult
import com.example.gestordepeliculas.data.repository.MovieRepository
import com.example.gestordepeliculas.ui.state.ResultVM
import javax.inject.Inject

class MovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {

    suspend fun getMovieById(idMovie: Int): ResultVM<MovieDetails> {
        return movieRepository.getMovieById(idMovie)
    }

    suspend fun findMovies(query: String): ResultVM<List<MovieResult>> {
        return movieRepository.findMovies(query)
    }

    suspend fun getMoviesDiscover(): ResultVM<List<MovieResult>> {
        return movieRepository.getMoviesDiscover()
    }

    suspend fun getProvidersByIDMovie(id_movie: Int): ResultVM<CountryProviderResults> {
        return movieRepository.getProvidersByIDMovie(id_movie)
    }

    suspend fun getMovieTrailer(id_movie: Int): ResultVM<List<TrailerMovieResult>> {
        return movieRepository.getMovieTrailer(id_movie)
    }

}