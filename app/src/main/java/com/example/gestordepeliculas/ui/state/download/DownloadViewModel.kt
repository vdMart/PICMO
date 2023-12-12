package com.example.gestordepeliculas.ui.state.download

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestordepeliculas.domain.model.HomeCategory
import com.example.gestordepeliculas.domain.model.movieserie.movie.MovieModel
import com.example.gestordepeliculas.domain.model.movieserie.movie.toDomain
import com.example.gestordepeliculas.domain.model.movieserie.movieseriepersonModel
import com.example.gestordepeliculas.domain.model.movieserie.serie.SerieModel
import com.example.gestordepeliculas.domain.model.movieserie.serie.toDomain
import com.example.gestordepeliculas.domain.usecase.MovieUseCase
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
class DownloadViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
    private val serieUseCase: SerieUseCase,
): ViewModel() {

    private var _downloadStateVM = MutableStateFlow<StateVM>(StateVM())
    val downloadStateVM: StateFlow<StateVM> = _downloadStateVM.asStateFlow()

    private var _movie = MutableStateFlow<MovieModel?>(null)
    val movie: StateFlow<MovieModel?> = _movie.asStateFlow()

    private var _serie = MutableStateFlow<SerieModel?>(null)
    val serie: StateFlow<SerieModel?> = _serie.asStateFlow()

    private var _listMovie = mutableListOf<MovieModel>()
    val listMovie: List<movieseriepersonModel> get() = _listMovie.toList()

    private var _listSerie = mutableListOf<SerieModel>()
    val listSerie: List<movieseriepersonModel> get() = _listSerie.toList()


    fun onEvent(event: DownloadEvent) {
        when (event) {

            /* Get by ID */
            is DownloadEvent.GetMoviesById -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _downloadStateVM.value = downloadStateVM.value.copy(isLoading = true)
                    val movies = mutableListOf<MovieModel>()

                    event.ids.forEach() { id ->

                        var movieResult = movieUseCase.getMovieById(id)
                        when(movieResult){
                            is ResultVM.Success -> {
                                movies.add(movieResult.data.toDomain())
                                Log.i("InfoTag", "Info on: DownloadViewModel.GetMoviesById,\n State: ${_downloadStateVM.value} \n Result: ${movieResult.data}")
                            }

                            is ResultVM.Error -> {
                                _downloadStateVM.value = downloadStateVM.value.copy(errorException = movieResult.exception, isSuccessful = false)
                                Log.e("ErrorTag", "Error on: DownloadViewModel.GetMoviesById,\n Message: ${_downloadStateVM.value.errorException?.message}")
                            }
                        }

                    }

                    _listMovie.clear()
                    _listMovie.addAll(movies)
                    _downloadStateVM.value = downloadStateVM.value.copy(isLoading = false)
                }
            }

            is DownloadEvent.GetSeriesById -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _downloadStateVM.value = downloadStateVM.value.copy(isLoading = true)
                    val series = mutableListOf<SerieModel>()

                    event.ids.forEach() { id ->

                        var serieResult = serieUseCase.getSerieById(id)
                        when(serieResult){
                            is ResultVM.Success -> {
                                series.add(serieResult.data.toDomain())
                                Log.i("InfoTag", "Info on: DownloadViewModel.GetSerieById,\n State: ${_downloadStateVM.value} \n Result: ${serieResult.data}")
                            }

                            is ResultVM.Error -> {
                                _downloadStateVM.value = downloadStateVM.value.copy(errorException = serieResult.exception, isSuccessful = false)
                                Log.e("ErrorTag", "Error on: DownloadViewModel.GetSerieById, \n Message: ${_downloadStateVM.value.errorException?.message}")
                            }
                        }

                    }

                    _listSerie.clear()
                    _listSerie.addAll(series)
                    _downloadStateVM.value = downloadStateVM.value.copy(isLoading = false)
                }
            }
        }
    }


}
