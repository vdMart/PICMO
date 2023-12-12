package com.example.gestordepeliculas.data.remote.api

import com.example.gestordepeliculas.data.remote.models.CountryProviderResults
import com.example.gestordepeliculas.data.remote.models.movie.MovieDetails
import com.example.gestordepeliculas.data.remote.models.movie.MovieResult
import com.example.gestordepeliculas.data.remote.models.serie.SerieResult
import com.example.gestordepeliculas.data.remote.models.movie.TrailerMovieResult
import com.example.gestordepeliculas.data.remote.models.person.PersonDetailsEntity
import com.example.gestordepeliculas.data.remote.models.person.PersonResult
import com.example.gestordepeliculas.data.remote.models.serie.SerieDetails
import com.example.gestordepeliculas.ui.state.ResultVM

interface ApiService {
    suspend fun getMovieById(idMovie: Int): ResultVM<MovieDetails>
    suspend fun findMovies(query: String): ResultVM<List<MovieResult>>
    suspend fun getMoviesDiscover(): ResultVM<List<MovieResult>>
    suspend fun getProvidersByIDMovie(idMovie: Int): ResultVM<CountryProviderResults>
    suspend fun getMovieTrailer(idMovie: Int): ResultVM<List<TrailerMovieResult>>

    suspend fun getSerieById(idSerie: Int): ResultVM<SerieDetails>
    suspend fun findSeries(query: String): ResultVM<List<SerieResult>>
    suspend fun getSeriesDiscover(): ResultVM<List<SerieResult>>
    suspend fun getProvidersByIDSeries(idSeries: Int): ResultVM<CountryProviderResults>

    suspend fun getPersonById(idPerson: Int): ResultVM<PersonDetailsEntity>
    suspend fun findPerson(query: String): ResultVM<List<PersonResult>>
    suspend fun getPersonPopular(): ResultVM<List<PersonResult>>
}