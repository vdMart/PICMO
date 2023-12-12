package com.example.gestordepeliculas.ui.state.details

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestordepeliculas.domain.model.movieserie.WatchProvidersModel
import com.example.gestordepeliculas.domain.model.movieserie.movie.MovieModel
import com.example.gestordepeliculas.domain.model.movieserie.movie.toDomain
import com.example.gestordepeliculas.domain.model.movieserie.person.PersonModel
import com.example.gestordepeliculas.domain.model.movieserie.person.toDomain
import com.example.gestordepeliculas.domain.model.movieserie.serie.SerieModel
import com.example.gestordepeliculas.domain.model.movieserie.serie.toDomain
import com.example.gestordepeliculas.domain.model.movieserie.toDomain
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
class DetailsViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
    private val serieUseCase: SerieUseCase,
    private val personUseCase: PersonUseCase
): ViewModel() {

    private var _detailsStateVM = MutableStateFlow<StateVM>(StateVM())
    val detailsStateVM: StateFlow<StateVM> = _detailsStateVM.asStateFlow()

    private var _providers by mutableStateOf<WatchProvidersModel?>(null)
    val providers: WatchProvidersModel? get() = _providers

    private var _movie by mutableStateOf<MovieModel?>(null)
    val movie: MovieModel? get() = _movie

    private var _serie by mutableStateOf<SerieModel?>(null)
    val serie: SerieModel? get() = _serie

    private var _person by mutableStateOf<PersonModel?>(null)
    val person: PersonModel? get() = _person


    fun onEvent(event: DetailsEvent) {
        when (event) {

            /* Get by ID */
            is DetailsEvent.GetMovieById -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _detailsStateVM.value = detailsStateVM.value.copy(isLoading = true)
                    var movieResult = movieUseCase.getMovieById(event.id)

                    when(movieResult){
                        is ResultVM.Success -> {
                            _movie = movieResult.data.toDomain()
                            Log.i("InfoTag", "Info on: DetailsEvent.GetMovieById, \n State: ${_detailsStateVM.value} \n Result: ${movieResult.data}")
                        }

                        is ResultVM.Error -> {
                            _movie = null
                            _detailsStateVM.value = detailsStateVM.value.copy(errorException = movieResult.exception, isSuccessful = false)
                            Log.e("ErrorTag", "Error on: DetailsEvent.GetMovieById, \n Message: ${_detailsStateVM.value.errorException?.message}")
                        }
                    }

                    _detailsStateVM.value = detailsStateVM.value.copy(isLoading = false)
                }
            }

            is DetailsEvent.GetSerieById -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _detailsStateVM.value = detailsStateVM.value.copy(isLoading = true)
                    var serieResult = serieUseCase.getSerieById(event.id)

                    when(serieResult){
                        is ResultVM.Success -> {
                            _serie = serieResult.data.toDomain()
                            Log.i("InfoTag", "Info on: DetailsEvent.GetSerieById, \n State: ${_detailsStateVM.value} \n Result: ${serieResult.data}")
                        }

                        is ResultVM.Error -> {
                            _serie = null
                            _detailsStateVM.value = detailsStateVM.value.copy(errorException = serieResult.exception, isSuccessful = false)
                            Log.e("ErrorTag", "Error on: DetailsEvent.GetSerieById, \n Message: ${_detailsStateVM.value.errorException?.message}")
                        }
                    }

                    _detailsStateVM.value = detailsStateVM.value.copy(isLoading = false)
                }
            }

            is DetailsEvent.GetPersonById -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _detailsStateVM.value = detailsStateVM.value.copy(isLoading = true)
                    var personResult = personUseCase.getPersonById(event.id)

                    when(personResult){
                        is ResultVM.Success -> {
                            _person = personResult.data.toDomain()
                            Log.i("InfoTag", "Info on: DetailsEvent.GetPersonById, \n State: ${_detailsStateVM.value} \n Result: ${personResult.data}")
                        }

                        is ResultVM.Error -> {
                            _person = null
                            _detailsStateVM.value = detailsStateVM.value.copy(errorException = personResult.exception, isSuccessful = false)
                            Log.e("ErrorTag", "Error on: DetailsEvent.GetPersonById, \n Message: ${_detailsStateVM.value.errorException?.message}")
                        }
                    }

                    _detailsStateVM.value = detailsStateVM.value.copy(isLoading = false)
                }
            }

            is DetailsEvent.GetMovieProviderById -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _detailsStateVM.value = detailsStateVM.value.copy(isLoading = true)
                    var movieProvidersResult = movieUseCase.getProvidersByIDMovie(event.id)

                    when(movieProvidersResult){
                        is ResultVM.Success -> {
                            _providers = movieProvidersResult.data.toDomain().ES
                            Log.i("InfoTag", "Info on: DetailsEvent.GetProviderMovieById, \n State: ${_detailsStateVM.value} \n Result: ${movieProvidersResult.data}")
                        }

                        is ResultVM.Error -> {
                            _providers = null
                            _detailsStateVM.value = detailsStateVM.value.copy(errorException = movieProvidersResult.exception, isSuccessful = false)
                            Log.e("ErrorTag", "Error on: DetailsEvent.GetProviderMovieById, \n Message: ${_detailsStateVM.value.errorException?.message}")
                        }
                    }

                    _detailsStateVM.value = detailsStateVM.value.copy(isLoading = false)
                }
            }

            is DetailsEvent.GetSerieProviderById -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _detailsStateVM.value = detailsStateVM.value.copy(isLoading = true)
                    var serieProvidersResult = serieUseCase.getProvidersByIDSeries(event.id)

                    when(serieProvidersResult){
                        is ResultVM.Success -> {
                            _providers = serieProvidersResult.data.toDomain().ES
                            Log.i("InfoTag", "Info on: DetailsEvent.GetProviderSerieById, \n State: ${_detailsStateVM.value} \n Result: ${serieProvidersResult.data}")
                        }

                        is ResultVM.Error -> {
                            _providers = null
                            _detailsStateVM.value = detailsStateVM.value.copy(errorException = serieProvidersResult.exception, isSuccessful = false)
                            Log.e("ErrorTag", "Error on: DetailsEvent.GetProviderSerieById, \n Message: ${_detailsStateVM.value.errorException?.message}")
                        }
                    }

                    _detailsStateVM.value = detailsStateVM.value.copy(isLoading = false)
                }
            }
        }
    }


}