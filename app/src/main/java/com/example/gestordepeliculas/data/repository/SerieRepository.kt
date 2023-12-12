package com.example.gestordepeliculas.data.repository

import com.example.gestordepeliculas.data.remote.api.ApiService
import com.example.gestordepeliculas.data.remote.models.CountryProviderResults
import com.example.gestordepeliculas.data.remote.models.serie.SerieDetails
import com.example.gestordepeliculas.data.remote.models.serie.SerieResult
import com.example.gestordepeliculas.ui.state.ResultVM
import javax.inject.Inject

class SerieRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getSerieById(idSerie: Int): ResultVM<SerieDetails> {
        return apiService.getSerieById(idSerie)
    }

    suspend fun findSeries(query: String): ResultVM<List<SerieResult>> {
        return apiService.findSeries(query)
    }

    suspend fun getSeriesDiscover(): ResultVM<List<SerieResult>> {
        return apiService.getSeriesDiscover()
    }

    suspend fun getProvidersByIDSeries(idSerie: Int): ResultVM<CountryProviderResults> {
        return apiService.getProvidersByIDSeries(idSerie)
    }

}