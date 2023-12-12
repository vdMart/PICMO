package com.example.gestordepeliculas.domain.usecase

import com.example.gestordepeliculas.data.remote.models.CountryProviderResults
import com.example.gestordepeliculas.data.remote.models.serie.SerieDetails
import com.example.gestordepeliculas.data.remote.models.serie.SerieResult
import com.example.gestordepeliculas.data.repository.SerieRepository
import com.example.gestordepeliculas.ui.state.ResultVM
import javax.inject.Inject

class SerieUseCase @Inject constructor(
    private val serieRepository: SerieRepository
) {

    suspend fun getSerieById(idSerie: Int): ResultVM<SerieDetails> {
        return serieRepository.getSerieById(idSerie)
    }

    suspend fun findSeries(query: String): ResultVM<List<SerieResult>> {
        return serieRepository.findSeries(query)
    }

    suspend fun getSeriesDiscover(): ResultVM<List<SerieResult>> {
        return serieRepository.getSeriesDiscover()
    }

    suspend fun getProvidersByIDSeries(idSerie: Int): ResultVM<CountryProviderResults>{
        return serieRepository.getProvidersByIDSeries(idSerie)
    }

}
