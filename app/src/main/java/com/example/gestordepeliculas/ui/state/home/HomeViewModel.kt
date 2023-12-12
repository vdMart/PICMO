package com.example.gestordepeliculas.ui.state.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestordepeliculas.domain.model.HomeCategory
import com.example.gestordepeliculas.domain.model.HomeCategoryMovie
import com.example.gestordepeliculas.domain.model.HomeCategoryPerson
import com.example.gestordepeliculas.domain.model.HomeCategorySerie
import com.example.gestordepeliculas.domain.model.movieserie.movie.MovieModel
import com.example.gestordepeliculas.domain.model.movieserie.movie.toDomain
import com.example.gestordepeliculas.domain.model.movieserie.movieseriepersonModel
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
class HomeViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
    private val serieUseCase: SerieUseCase,
    private val personUseCase: PersonUseCase
): ViewModel() {

    private var _homeStateVM = MutableStateFlow<StateVM>(StateVM())
    val homeStateVM: StateFlow<StateVM> = _homeStateVM.asStateFlow()

    private var _listMovieSeriePerson = mutableMapOf<HomeCategory, MutableList<movieseriepersonModel>>()
    val listMovieSeriePerson: MutableMap<HomeCategory, MutableList<movieseriepersonModel>> get() = _listMovieSeriePerson


    fun onEvent(event: HomeEvent) {
        when (event) {

            /* Get by ID */
            is HomeEvent.GetMoviesById -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _homeStateVM.value = homeStateVM.value.copy(isLoading = true)
                    val movies = mutableListOf<MovieModel>()

                    event.ids.forEach() { id ->

                        var movieResult = movieUseCase.getMovieById(id)
                        when(movieResult){
                            is ResultVM.Success -> {
                                movies.add(movieResult.data.toDomain())
                                Log.i("InfoTag", "Info on: HomeViewModel.GetMovieById, \n State: ${_homeStateVM.value} \n Result: ${movieResult.data}")
                            }

                            is ResultVM.Error -> {
                                _homeStateVM.value = homeStateVM.value.copy(errorException = movieResult.exception, isSuccessful = false)
                                Log.e("ErrorTag", "Error on: HomeViewModel.GetMovieById, \n Message: ${_homeStateVM.value.errorException?.message}")
                            }
                        }

                    }

                    _listMovieSeriePerson[event.homeCategory] = movies.toMutableList()
                    _homeStateVM.value = homeStateVM.value.copy(isLoading = false)
                }
            }

            is HomeEvent.GetSeriesById -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _homeStateVM.value = homeStateVM.value.copy(isLoading = true)
                    val series = mutableListOf<SerieModel>()

                    event.ids.forEach() { id ->

                        var serieResult = serieUseCase.getSerieById(id)
                        when(serieResult){
                            is ResultVM.Success -> {
                                series.add(serieResult.data.toDomain())
                                Log.i("InfoTag", "Info on: HomeViewModel.GetSerieById, \n State: ${_homeStateVM.value} \n Result: ${serieResult.data}")
                            }

                            is ResultVM.Error -> {
                                _homeStateVM.value = homeStateVM.value.copy(errorException = serieResult.exception, isSuccessful = false)
                                Log.e("ErrorTag", "Error on: HomeViewModel.GetSerieById, \n Message: ${_homeStateVM.value.errorException?.message}")
                            }
                        }

                    }

                    _listMovieSeriePerson[event.homeCategory] = series.toMutableList()
                    _homeStateVM.value = homeStateVM.value.copy(isLoading = false)
                }
            }

            is HomeEvent.GetPersonsById -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _homeStateVM.value = homeStateVM.value.copy(isLoading = true)
                    val persons = mutableListOf<PersonModel>()

                    event.ids.forEach() { id ->

                        var personResult = personUseCase.getPersonById(id)
                        when(personResult){
                            is ResultVM.Success -> {
                                persons.add(personResult.data.toDomain())
                                Log.i("InfoTag", "Info on: HomeViewModel.GetPersonById, \n State: ${_homeStateVM.value} \n Result: ${personResult.data}")
                            }

                            is ResultVM.Error -> {
                                _homeStateVM.value = homeStateVM.value.copy(errorException = personResult.exception, isSuccessful = false)
                                Log.e("ErrorTag", "Error on: HomeViewModel.GetPersonById, \n Message: ${_homeStateVM.value.errorException?.message}")
                            }
                        }

                    }

                    _listMovieSeriePerson[event.homeCategory] = persons.toMutableList()
                    _homeStateVM.value = homeStateVM.value.copy(isLoading = false)
                }
            }


            /* Discover Popular */
            HomeEvent.GetListMoviesDiscover -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _homeStateVM.value = homeStateVM.value.copy(isLoading = true)
                    var movieResult = movieUseCase.getMoviesDiscover()

                    when(movieResult){
                        is ResultVM.Success -> {
                            _listMovieSeriePerson[HomeCategoryMovie.MovieDiscover] = ResultVM.Success(
                                data = movieResult.data.map {
                                    it.toDomain()
                                }
                            ).data.toMutableList()
                            _homeStateVM.value = homeStateVM.value.copy(errorException = null, isSuccessful = true)
                            Log.i("InfoTag", "Info on: HomeViewModel.GetListMoviesDiscover,\n State: ${_homeStateVM.value} \n Result: ${movieResult.data}")
                        }

                        is ResultVM.Error -> {
                            _listMovieSeriePerson[HomeCategoryMovie.MovieDiscover] = emptyList<MovieModel>().toMutableList()
                            _homeStateVM.value = homeStateVM.value.copy(errorException = movieResult.exception, isSuccessful = false)
                            Log.e("ErrorTag", "Error on: HomeViewModel.GetListMoviesDiscover,\n Message: ${_homeStateVM.value.errorException?.message}")
                        }
                    }

                    _homeStateVM.value = homeStateVM.value.copy(isLoading = false)
                }
            }

            HomeEvent.GetListSeriesDiscover -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _homeStateVM.value = homeStateVM.value.copy(isLoading = true)
                    var serieResult = serieUseCase.getSeriesDiscover()

                    when(serieResult){
                        is ResultVM.Success -> {
                            _listMovieSeriePerson[HomeCategorySerie.SerieDiscover] = ResultVM.Success(
                                data = serieResult.data.map {
                                    it.toDomain()
                                }
                            ).data.toMutableList()
                            _homeStateVM.value = homeStateVM.value.copy(errorException = null, isSuccessful = true)
                            Log.i("InfoTag", "Info on: MovieSerieEvent.GetListSerie, \n State: ${_homeStateVM.value} \n Result: ${serieResult.data}")
                        }

                        is ResultVM.Error -> {
                            _listMovieSeriePerson[HomeCategorySerie.SerieDiscover] = emptyList<SerieModel>().toMutableList()
                            _homeStateVM.value = homeStateVM.value.copy(errorException = serieResult.exception, isSuccessful = false)
                            Log.e("ErrorTag", "Error on: MovieSerieEvent.GetListSerie, \n Message: ${_homeStateVM.value.errorException?.message}")
                        }
                    }

                    _homeStateVM.value = homeStateVM.value.copy(isLoading = false)
                }
            }

            HomeEvent.GetListPersonPopular -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _homeStateVM.value = homeStateVM.value.copy(isLoading = true)
                    var personResult = personUseCase.getPersonPopular()

                    when(personResult){
                        is ResultVM.Success -> {
                            _listMovieSeriePerson[HomeCategoryPerson.PersonDirector] = personResult.data.map { it.toDomain() }.toMutableList()
                            _listMovieSeriePerson[HomeCategoryPerson.PersonActor] = personResult.data.map { it.toDomain() }.toMutableList()
                            Log.i("InfoTag", "Info on: MovieSerieEvent.GetListPersonPopular, \n State: ${_homeStateVM.value} \n Result: ${personResult.data}")
                        }

                        is ResultVM.Error -> {
                            _listMovieSeriePerson[HomeCategoryPerson.PersonDirector] = emptyList<PersonModel>().toMutableList()
                            _listMovieSeriePerson[HomeCategoryPerson.PersonActor] = emptyList<PersonModel>().toMutableList()
                            _homeStateVM.value = homeStateVM.value.copy(errorException = personResult.exception, isSuccessful = false)
                            Log.e("ErrorTag", "Error on: MovieSerieEvent.GetListPersonPopular, \n Message: ${_homeStateVM.value.errorException?.message}")
                        }
                    }

                    _homeStateVM.value = homeStateVM.value.copy(isLoading = false)
                }
            }
        }
    }


}