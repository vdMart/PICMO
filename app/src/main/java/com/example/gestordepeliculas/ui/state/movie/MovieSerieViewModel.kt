package com.example.gestordepeliculas.ui.state.movie

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestordepeliculas.domain.model.movieserie.movie.MovieModel
import com.example.gestordepeliculas.domain.model.movieserie.movie.toDomain
import com.example.gestordepeliculas.domain.model.movieserie.person.PersonModel
import com.example.gestordepeliculas.domain.model.movieserie.person.toDomain
import com.example.gestordepeliculas.domain.model.movieserie.serie.SerieModel
import com.example.gestordepeliculas.domain.model.movieserie.serie.toDomain
import com.example.gestordepeliculas.domain.usecase.MovieUseCase
import com.example.gestordepeliculas.domain.usecase.PersonUseCase
import com.example.gestordepeliculas.domain.usecase.SerieUseCase
import com.example.gestordepeliculas.ui.state.ResultVM
import com.example.gestordepeliculas.ui.state.StateVM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieSerieViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
    private val serieUseCase: SerieUseCase,
    private val personUseCase: PersonUseCase
): ViewModel() {

    private var _movieSerieStateVM = MutableStateFlow<StateVM>(StateVM())
    val movieSerieStateVM: StateFlow<StateVM> = _movieSerieStateVM.asStateFlow()

    private var _movieList = mutableListOf<MovieModel>()
    val movieList: List<MovieModel> get() = _movieList

    private var _serieList = mutableListOf<SerieModel>()
    val serieList: List<SerieModel> get() = _serieList

    private var _personList = mutableListOf<PersonModel>()
    val personList: List<PersonModel> get() = _personList


    fun onEvent(event: MovieSerieEvent) {
        when (event) {

            /* Movie */
            is MovieSerieEvent.GetFindMovie -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _movieSerieStateVM.value = movieSerieStateVM.value.copy(isLoading = true)
                    var movieResult = movieUseCase.findMovies(event.query)

                    when(movieResult){
                        is ResultVM.Success -> {
                            _movieList = movieResult.data.map { it.toDomain() }.toMutableList()
                            Log.i("InfoTag", "Info on: MovieSerieEvent.GetFindMovie, \n State: ${_movieSerieStateVM.value} \n Result: ${movieResult.data}")
                        }

                        is ResultVM.Error -> {
                            _movieList = emptyList<MovieModel>().toMutableList()
                            _movieSerieStateVM.value = movieSerieStateVM.value.copy(errorException = movieResult.exception, isSuccessful = false)
                            Log.e("ErrorTag", "Error on: MovieSerieEvent.GetFindMovie, \n Message: ${_movieSerieStateVM.value.errorException?.message}")
                        }
                    }

                    _movieSerieStateVM.value = movieSerieStateVM.value.copy(isLoading = false)
                }
            }

            MovieSerieEvent.GetListMovie -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _movieSerieStateVM.value = movieSerieStateVM.value.copy(isLoading = true)
                    var movieResult = movieUseCase.getMoviesDiscover()

                    when(movieResult){
                        is ResultVM.Success -> {
                            _movieList = ResultVM.Success(
                                data = movieResult.data.map {
                                    it.toDomain()
                                }
                            ).data.toMutableList()
                            _movieSerieStateVM.value = movieSerieStateVM.value.copy(errorException = null, isSuccessful = true)
                            Log.i("InfoTag", "Info on: MovieSerieEvent.GetListMovie, \n State: ${_movieSerieStateVM.value} \n Result: ${movieResult.data}")
                        }

                        is ResultVM.Error -> {
                            _movieList = emptyList<MovieModel>().toMutableList()
                            _movieSerieStateVM.value = movieSerieStateVM.value.copy(errorException = movieResult.exception, isSuccessful = false)
                            Log.e("ErrorTag", "Error on: MovieSerieEvent.GetListMovie, \n Message: ${_movieSerieStateVM.value.errorException?.message}")
                        }
                    }

                    _movieSerieStateVM.value = movieSerieStateVM.value.copy(isLoading = false)
                }
            }

            /* Serie */
            is MovieSerieEvent.GetFindSerie -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _movieSerieStateVM.value = movieSerieStateVM.value.copy(isLoading = true)
                    var serieResult = serieUseCase.findSeries(event.query)

                    when(serieResult){
                        is ResultVM.Success -> {
                            _serieList = serieResult.data.map { it.toDomain() }.toMutableList()
                            Log.i("InfoTag", "Info on: MovieSerieEvent.GetFindSerie, \n State: ${_movieSerieStateVM.value} \n Result: ${serieResult.data}")
                        }

                        is ResultVM.Error -> {
                            _serieList = emptyList<SerieModel>().toMutableList()
                            _movieSerieStateVM.value = movieSerieStateVM.value.copy(errorException = serieResult.exception, isSuccessful = false)
                            Log.e("ErrorTag", "Error on: MovieSerieEvent.GetFindSerie, \n Message: ${_movieSerieStateVM.value.errorException?.message}")
                        }
                    }

                    _movieSerieStateVM.value = movieSerieStateVM.value.copy(isLoading = false)
                }
            }

            MovieSerieEvent.GetListSerie -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _movieSerieStateVM.value = movieSerieStateVM.value.copy(isLoading = true)
                    var serieResult = serieUseCase.getSeriesDiscover()

                    when(serieResult){
                        is ResultVM.Success -> {
                            _serieList = ResultVM.Success(
                                data = serieResult.data.map {
                                    it.toDomain()
                                }
                            ).data.toMutableList()
                            _movieSerieStateVM.value = movieSerieStateVM.value.copy(errorException = null, isSuccessful = true)
                            Log.i("InfoTag", "Info on: MovieSerieEvent.GetListSerie, \n State: ${_movieSerieStateVM.value} \n Result: ${serieResult.data}")
                        }

                        is ResultVM.Error -> {
                            _serieList = emptyList<SerieModel>().toMutableList()
                            _movieSerieStateVM.value = movieSerieStateVM.value.copy(errorException = serieResult.exception, isSuccessful = false)
                            Log.e("ErrorTag", "Error on: MovieSerieEvent.GetListSerie, \n Message: ${_movieSerieStateVM.value.errorException?.message}")
                        }
                    }

                    _movieSerieStateVM.value = movieSerieStateVM.value.copy(isLoading = false)
                }
            }

            /* Person */
            is MovieSerieEvent.GetFindPerson -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _movieSerieStateVM.value = movieSerieStateVM.value.copy(isLoading = true)
                    var personResult = personUseCase.findPerson(event.query)

                    when(personResult){
                        is ResultVM.Success -> {
                            _personList = personResult.data.map { it.toDomain() }.toMutableList()
                            Log.i("InfoTag", "Info on: MovieSerieEvent.GetFindPerson, \n State: ${_movieSerieStateVM.value} \n Result: ${personResult.data}")
                        }

                        is ResultVM.Error -> {
                            _personList = emptyList<PersonModel>().toMutableList()
                            _movieSerieStateVM.value = movieSerieStateVM.value.copy(errorException = personResult.exception, isSuccessful = false)
                            Log.e("ErrorTag", "Error on: MovieSerieEvent.GetFindPerson, \n Message: ${_movieSerieStateVM.value.errorException?.message}")
                        }
                    }

                    _movieSerieStateVM.value = movieSerieStateVM.value.copy(isLoading = false)
                }
            }
            MovieSerieEvent.GetListPerson -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _movieSerieStateVM.value = movieSerieStateVM.value.copy(isLoading = true)
                    var personResult = personUseCase.getPersonPopular()

                    when(personResult){
                        is ResultVM.Success -> {
                            _personList = personResult.data.map { it.toDomain() }.toMutableList()
                            Log.i("InfoTag", "Info on: MovieSerieEvent.GetListPersonPopular, \n State: ${_movieSerieStateVM.value} \n Result: ${personResult.data}")
                        }

                        is ResultVM.Error -> {
                            _personList = emptyList<PersonModel>().toMutableList()
                            _movieSerieStateVM.value = movieSerieStateVM.value.copy(errorException = personResult.exception, isSuccessful = false)
                            Log.e("ErrorTag", "Error on: MovieSerieEvent.GetListPersonPopular, \n Message: ${_movieSerieStateVM.value.errorException?.message}")
                        }
                    }

                    _movieSerieStateVM.value = movieSerieStateVM.value.copy(isLoading = false)
                }
            }
        }
    }


}
