package com.example.gestordepeliculas.data.repository

import com.example.gestordepeliculas.data.remote.api.ApiService
import com.example.gestordepeliculas.data.remote.models.person.PersonDetailsEntity
import com.example.gestordepeliculas.data.remote.models.person.PersonResult
import com.example.gestordepeliculas.ui.state.ResultVM
import javax.inject.Inject

class PersonRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getPersonById(idPerson: Int): ResultVM<PersonDetailsEntity> {
        return apiService.getPersonById(idPerson)
    }

    suspend fun findPerson(query: String): ResultVM<List<PersonResult>> {
        return apiService.findPerson(query)
    }

    suspend fun getPersonPopular(): ResultVM<List<PersonResult>> {
        return apiService.getPersonPopular()
    }

}