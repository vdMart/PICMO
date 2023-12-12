package com.example.gestordepeliculas.domain.usecase

import com.example.gestordepeliculas.data.remote.models.person.PersonDetailsEntity
import com.example.gestordepeliculas.data.remote.models.person.PersonResult
import com.example.gestordepeliculas.data.repository.PersonRepository
import com.example.gestordepeliculas.ui.state.ResultVM
import javax.inject.Inject

class PersonUseCase @Inject constructor(
    private val personRepository: PersonRepository
) {

    suspend fun getPersonById(idPerson: Int): ResultVM<PersonDetailsEntity> {
        return personRepository.getPersonById(idPerson)
    }

    suspend fun findPerson(query: String): ResultVM<List<PersonResult>> {
        return personRepository.findPerson(query)
    }

    suspend fun getPersonPopular(): ResultVM<List<PersonResult>> {
        return personRepository.getPersonPopular()
    }

}