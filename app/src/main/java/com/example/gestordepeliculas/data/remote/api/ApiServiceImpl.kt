package com.example.gestordepeliculas.data.remote.api

import android.util.Log
import com.example.gestordepeliculas.data.remote.models.CountryProviderResults
import com.example.gestordepeliculas.data.remote.models.movie.MovieDetails
import com.example.gestordepeliculas.data.remote.models.movie.MovieResponse
import com.example.gestordepeliculas.data.remote.models.movie.MovieResult
import com.example.gestordepeliculas.data.remote.models.serie.SerieResponse
import com.example.gestordepeliculas.data.remote.models.serie.SerieResult
import com.example.gestordepeliculas.data.remote.models.movie.TrailerMovieResult
import com.example.gestordepeliculas.data.remote.models.movie.TrailerResponse
import com.example.gestordepeliculas.data.remote.models.serie.SerieDetails
import com.example.gestordepeliculas.data.remote.models.WatchProvidersResponse
import com.example.gestordepeliculas.data.remote.models.person.PersonDetailsEntity
import com.example.gestordepeliculas.data.remote.models.person.PersonResponse
import com.example.gestordepeliculas.data.remote.models.person.PersonResult
import com.example.gestordepeliculas.ui.state.ResultVM
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get

class ApiServiceImpl(
    private val client: HttpClient
): ApiService {


    /*
    * MOVIES
    * */

    override suspend fun getMovieById(idMovie: Int): ResultVM<MovieDetails> {
        Log.d("DebugTag", "Debug on: ApiServiceImpl.getMovieById,\n  Ruta: ${HttpRoutes.MOVIE_BY_ID+"/$idMovie"+HttpRoutes.API_KEY}")
        return try {
            ResultVM.Success(data = client.get(HttpRoutes.MOVIE_BY_ID+"/$idMovie"+HttpRoutes.API_KEY).body<MovieDetails>())
            ResultVM.Success(data = client.get(HttpRoutes.MOVIE_BY_ID+"/$idMovie"+HttpRoutes.API_KEY).body<MovieDetails>())
        } catch(ex: RedirectResponseException) {
            // 3xx - responses
            Log.e("ErrorTag", "Error on: ApiService getMovieById, \n Message: ${ex.response.status.description}")
            ResultVM.Error(exception = ex)
        } catch(ex: ClientRequestException) {
            // 4xx - responses
            Log.e("ErrorTag", "Error on: ApiService getMovieById, \n Message: ${ex.response.status.description}")
            ResultVM.Error(exception = ex)
        } catch(ex: ServerResponseException) {
            // 5xx - responses
            Log.e("ErrorTag", "Error on: ApiService getMovieById(), \n Message: ${ex.response.status.description}")
            ResultVM.Error(exception = ex)
        } catch(ex: Exception) {
            Log.e("ErrorTag", "Error on: ApiService getMovieById(), \n Message: ${ex.message}")
            ResultVM.Error(exception = ex)
        }
    }

    override suspend fun findMovies(query: String): ResultVM<List<MovieResult>> {
        Log.d("DebugTag", "Debug on: ApiServiceImpl.findMovies,\n  Ruta: ${HttpRoutes.FIND_MOVIE_BY_QUERY+"/$query"}")
        return try {
            ResultVM.Success(data = client.get(HttpRoutes.FIND_MOVIE_BY_QUERY+"/$query").body<MovieResponse>().results)
        } catch(ex: RedirectResponseException) {
            // 3xx - responses
            Log.e("ErrorTag", "Error on: ApiService findMovies(), \n Message: ${ex.response.status.description}")
            ResultVM.Error(exception = ex)
        } catch(ex: ClientRequestException) {
            // 4xx - responses
            Log.e("ErrorTag", "Error on: ApiService findMovies(), \n Message: ${ex.response.status.description}")
            ResultVM.Error(exception = ex)
        } catch(ex: ServerResponseException) {
            // 5xx - responses
            Log.e("ErrorTag", "Error on: ApiService findMovies(), \n Message: ${ex.response.status.description}")
            ResultVM.Error(exception = ex)
        } catch(ex: Exception) {
            Log.e("ErrorTag", "Error on: ApiService findMovies(), \n Message: ${ex.message}")
            ResultVM.Error(exception = ex)
        }
    }

    override suspend fun getMoviesDiscover(): ResultVM<List<MovieResult>> {
        Log.d("DebugTag", "Debug on: ApiServiceImpl.getMoviesDiscover,\n  Ruta: ${HttpRoutes.MOVIE_DISCOVER}")
        return try {
            ResultVM.Success(data = client.get(HttpRoutes.MOVIE_DISCOVER).body<MovieResponse>().results)
        } catch(ex: RedirectResponseException) {
            // 3xx - responses
            Log.e("ErrorTag", "Error on: ApiService getMoviesDiscover, \n Message: ${ex.response.status.description}")
            ResultVM.Error(exception = ex)
        } catch(ex: ClientRequestException) {
            // 4xx - responses
            Log.e("ErrorTag", "Error on: ApiService getMoviesDiscover, \n Message: ${ex.response.status.description}")
            ResultVM.Error(exception = ex)
        } catch(ex: ServerResponseException) {
            // 5xx - responses
            Log.e("ErrorTag", "Error on: ApiService getMoviesDiscover, \n Message: ${ex.response.status.description}")
            ResultVM.Error(exception = ex)
        } catch(ex: Exception) {
            Log.e("ErrorTag", "Error on: ApiService getMoviesDiscover, \n Message: ${ex.message}")
            ResultVM.Error(exception = ex)
        }
    }

    override suspend fun getProvidersByIDMovie(idMovie: Int): ResultVM<CountryProviderResults> {
        Log.d("DebugTag", "Debug on: ApiServiceImpl.getProvidersByIDMovie,\n  Ruta: ${HttpRoutes.MOVIE_PROVIDERS1+"/$idMovie"+HttpRoutes.MOVIE_PROVIDERS2}")
        return try {
            ResultVM.Success(data = client.get(HttpRoutes.MOVIE_PROVIDERS1+"/$idMovie"+HttpRoutes.MOVIE_PROVIDERS2).body<WatchProvidersResponse>().results)
        } catch(ex: RedirectResponseException) {
            // 3xx - responses
            Log.e("ErrorTag", "Error on: ApiService getProvidersByIDMovie(), \n Message: ${ex.response.status.description}")
            ResultVM.Error(exception = ex)
        } catch(ex: ClientRequestException) {
            // 4xx - responses
            Log.e("ErrorTag", "Error on: ApiService getProvidersByIDMovie(), \n Message: ${ex.response.status.description}")
            ResultVM.Error(exception = ex)
        } catch(ex: ServerResponseException) {
            // 5xx - responses
            Log.e("ErrorTag", "Error on: ApiService getProvidersByIDMovie(), \n Message: ${ex.response.status.description}")
            ResultVM.Error(exception = ex)
        } catch(ex: Exception) {
            Log.e("ErrorTag", "Error on: ApiService getProvidersByIDMovie(), \n Message: ${ex.message}")
            ResultVM.Error(exception = ex)
        }
    }

    override suspend fun getMovieTrailer(idMovie: Int): ResultVM<List<TrailerMovieResult>> {
        Log.d("DebugTag", "Debug on: ApiServiceImpl.getMovieTrailer,\n  Ruta: ${HttpRoutes.TRAILER_MOVIE1+"/$idMovie"+HttpRoutes.TRAILER_MOVIE2}")
        return try {
            ResultVM.Success(data = client.get(HttpRoutes.TRAILER_MOVIE1+"/$idMovie"+HttpRoutes.TRAILER_MOVIE2).body<TrailerResponse>().results)
        } catch(ex: RedirectResponseException) {
            // 3xx - responses
            Log.e("ErrorTag", "Error on: ApiService getMovieTrailer(), \n Message: ${ex.response.status.description}")
            ResultVM.Error(exception = ex)
        } catch(ex: ClientRequestException) {
            // 4xx - responses
            Log.e("ErrorTag", "Error on: ApiService getMovieTrailer(), \n Message: ${ex.response.status.description}")
            ResultVM.Error(exception = ex)
        } catch(ex: ServerResponseException) {
            // 5xx - responses
            Log.e("ErrorTag", "Error on: ApiService getMovieTrailer(), \n Message: ${ex.response.status.description}")
            ResultVM.Error(exception = ex)
        } catch(ex: Exception) {
            Log.e("ErrorTag", "Error on: ApiService getMovieTrailer(), \n Message: ${ex.message}")
            ResultVM.Error(exception = ex)
        }
    }



    /*
    * SERIES
    * */

    override suspend fun getSerieById(idSerie: Int): ResultVM<SerieDetails> {
        Log.d("DebugTag", "Debug on: ApiServiceImpl.getSerieById,\n  Ruta: ${HttpRoutes.SERIE_BY_ID+"/$idSerie"+HttpRoutes.API_KEY}")
        return try {
            ResultVM.Success(data = client.get(HttpRoutes.SERIE_BY_ID+"/$idSerie"+HttpRoutes.API_KEY).body<SerieDetails>())
        } catch(ex: RedirectResponseException) {
            // 3xx - responses
            Log.e("ErrorTag", "Error on: ApiService getSerieById(), \n Message: ${ex.response.status.description}")
            ResultVM.Error(exception = ex)
        } catch(ex: ClientRequestException) {
            // 4xx - responses
            Log.e("ErrorTag", "Error on: ApiService getSerieById(), \n Message: ${ex.response.status.description}")
            ResultVM.Error(exception = ex)
        } catch(ex: ServerResponseException) {
            // 5xx - responses
            Log.e("ErrorTag", "Error on: ApiService getSerieById(), \n Message: ${ex.response.status.description}")
            ResultVM.Error(exception = ex)
        } catch(ex: Exception) {
            Log.e("ErrorTag", "Error on: ApiService getSerieById(), \n Message: ${ex.message}")
            ResultVM.Error(exception = ex)
        }
    }

    override suspend fun findSeries(query: String): ResultVM<List<SerieResult>> {
        Log.d("DebugTag", "Debug on: ApiServiceImpl.findSeries,\n  Ruta: ${HttpRoutes.FIND_SERIE_BY_QUERY+"/$query"}")
        return try {
            ResultVM.Success(data = client.get(HttpRoutes.FIND_SERIE_BY_QUERY+"/$query").body<SerieResponse>().results)
        } catch(ex: RedirectResponseException) {
            // 3xx - responses
            Log.e("ErrorTag", "Error on: ApiService findSeries(), \n Message: ${ex.response.status.description}")
            ResultVM.Error(exception = ex)
        } catch(ex: ClientRequestException) {
            // 4xx - responses
            Log.e("ErrorTag", "Error on: ApiService findSeries(), \n Message: ${ex.response.status.description}")
            ResultVM.Error(exception = ex)
        } catch(ex: ServerResponseException) {
            // 5xx - responses
            Log.e("ErrorTag", "Error on: ApiService findSeries(), \n Message: ${ex.response.status.description}")
            ResultVM.Error(exception = ex)
        } catch(ex: Exception) {
            Log.e("ErrorTag", "Error on: ApiService findSeries(), \n Message: ${ex.message}")
            ResultVM.Error(exception = ex)
        }
    }

    override suspend fun getSeriesDiscover(): ResultVM<List<SerieResult>> {
        Log.d("DebugTag", "Debug on: ApiServiceImpl.getSeriesDiscover,\n  Ruta: ${HttpRoutes.SERIE_DISCOVER}")
        return try {
            ResultVM.Success(data = client.get(HttpRoutes.SERIE_DISCOVER).body<SerieResponse>().results)
        } catch(ex: RedirectResponseException) {
            // 3xx - responses
            Log.e("ErrorTag", "Error on: ApiService getSeriesDiscover(), \n Message: ${ex.response.status.description}")
            ResultVM.Error(exception = ex)
        } catch(ex: ClientRequestException) {
            // 4xx - responses
            Log.e("ErrorTag", "Error on: ApiService getSeriesDiscover(), \n Message: ${ex.response.status.description}")
            ResultVM.Error(exception = ex)
        } catch(ex: ServerResponseException) {
            // 5xx - responses
            Log.e("ErrorTag", "Error on: ApiService getSeriesDiscover(), \n Message: ${ex.response.status.description}")
            ResultVM.Error(exception = ex)
        } catch(ex: Exception) {
            Log.e("ErrorTag", "Error on: ApiService getSeriesDiscover(), \n Message: ${ex.message}")
            ResultVM.Error(exception = ex)
        }
    }

    override suspend fun getProvidersByIDSeries(idSeries: Int): ResultVM<CountryProviderResults> {
        Log.d("DebugTag", "Debug on: ApiServiceImpl.getProvidersByIDSeries,\n  Ruta: ${HttpRoutes.SERIE_PROVIDERS1+"/$idSeries"+HttpRoutes.SERIE_PROVIDERS2}")
        return try {
            ResultVM.Success(data = client.get(HttpRoutes.SERIE_PROVIDERS1+"/$idSeries"+HttpRoutes.SERIE_PROVIDERS2).body<WatchProvidersResponse>().results)
        } catch(ex: RedirectResponseException) {
            // 3xx - responses
            Log.e("ErrorTag", "Error on: ApiService getProvidersByIDSeries(), \n Message: ${ex.response.status.description}")
            ResultVM.Error(exception = ex)
        } catch(ex: ClientRequestException) {
            // 4xx - responses
            Log.e("ErrorTag", "Error on: ApiService getProvidersByIDSeries(), \n Message: ${ex.response.status.description}")
            ResultVM.Error(exception = ex)
        } catch(ex: ServerResponseException) {
            // 5xx - responses
            Log.e("ErrorTag", "Error on: ApiService getProvidersByIDSeries(), \n Message: ${ex.response.status.description}")
            ResultVM.Error(exception = ex)
        } catch(ex: Exception) {
            Log.e("ErrorTag", "Error on: ApiService getProvidersByIDSeries(), \n Message: ${ex.message}")
            ResultVM.Error(exception = ex)
        }
    }



    /*
    * PERSON
    * */

    override suspend fun getPersonById(idPerson: Int): ResultVM<PersonDetailsEntity> {
        Log.d("DebugTag", "Debug on: ApiServiceImpl.getPersonById,\n  Ruta: ${HttpRoutes.PERSON_BY_ID+"/$idPerson"+HttpRoutes.API_KEY}")
        return try {
            ResultVM.Success(data = client.get(HttpRoutes.PERSON_BY_ID+"/$idPerson"+HttpRoutes.API_KEY).body<PersonDetailsEntity>())
        } catch(ex: RedirectResponseException) {
            // 3xx - responses
            Log.e("ErrorTag", "Error on: ApiService getPersonById(), \n Message: ${ex.response.status.description}")
            ResultVM.Error(exception = ex)
        } catch(ex: ClientRequestException) {
            // 4xx - responses
            Log.e("ErrorTag", "Error on: ApiService getPersonById(), \n Message: ${ex.response.status.description}")
            ResultVM.Error(exception = ex)
        } catch(ex: ServerResponseException) {
            // 5xx - responses
            Log.e("ErrorTag", "Error on: ApiService getPersonById(), \n Message: ${ex.response.status.description}")
            ResultVM.Error(exception = ex)
        } catch(ex: Exception) {
            Log.e("ErrorTag", "Error on: ApiService getPersonById(), \n Message: ${ex.message}")
            ResultVM.Error(exception = ex)
        }
    }

    override suspend fun findPerson(query: String): ResultVM<List<PersonResult>> {
        Log.d("DebugTag", "Debug on: ApiServiceImpl.findPerson,\n  Ruta: ${HttpRoutes.FIND_PERSON_BY_QUERY+"$query"}")
        return try {
            ResultVM.Success(data = client.get(HttpRoutes.FIND_PERSON_BY_QUERY+"$query").body<PersonResponse>().results)
        } catch(ex: RedirectResponseException) {
            // 3xx - responses
            Log.e("ErrorTag", "Error on: ApiService findPerson(), \n Message: ${ex.response.status.description}")
            ResultVM.Error(exception = ex)
        } catch(ex: ClientRequestException) {
            // 4xx - responses
            Log.e("ErrorTag", "Error on: ApiService findPerson(), \n Message: ${ex.response.status.description}")
            ResultVM.Error(exception = ex)
        } catch(ex: ServerResponseException) {
            // 5xx - responses
            Log.e("ErrorTag", "Error on: ApiService findPerson(), \n Message: ${ex.response.status.description}")
            ResultVM.Error(exception = ex)
        } catch(ex: Exception) {
            Log.e("ErrorTag", "Error on: ApiService findPerson(), \n Message: ${ex.message}")
            ResultVM.Error(exception = ex)
        }
    }

    override suspend fun getPersonPopular(): ResultVM<List<PersonResult>> {
        Log.d("DebugTag", "Debug on: ApiServiceImpl.getPersonPopular,\n  Ruta: ${HttpRoutes.PERSON_POPULAR}")
        return try {
            ResultVM.Success(data = client.get(HttpRoutes.PERSON_POPULAR).body<PersonResponse>().results)
        } catch(ex: RedirectResponseException) {
            // 3xx - responses
            Log.e("ErrorTag", "Error on: ApiService getPersonPopular(), \n Message: ${ex.response.status.description}")
            ResultVM.Error(exception = ex)
        } catch(ex: ClientRequestException) {
            // 4xx - responses
            Log.e("ErrorTag", "Error on: ApiService getPersonPopular(), \n Message: ${ex.response.status.description}")
            ResultVM.Error(exception = ex)
        } catch(ex: ServerResponseException) {
            // 5xx - responses
            Log.e("ErrorTag", "Error on: ApiService getPersonPopular(), \n Message: ${ex.response.status.description}")
            ResultVM.Error(exception = ex)
        } catch(ex: Exception) {
            Log.e("ErrorTag", "Error on: ApiService getPersonPopular(), \n Message: ${ex.message}")
            ResultVM.Error(exception = ex)
        }
    }

}
