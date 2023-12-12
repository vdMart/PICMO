package com.example.gestordepeliculas.ui.elements.details.person

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.gestordepeliculas.R
import com.example.gestordepeliculas.domain.model.movieserie.person.PersonModel
import com.example.gestordepeliculas.ui.elements.details._common.ImgPoster
import com.example.gestordepeliculas.ui.state.user.UserViewModel

@Composable
fun PersonScreen(person: PersonModel, userVM: UserViewModel) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Top
            ) {
                ImgPoster(person.photo_path?:"")
                DetailsPerson("${person.id}", person.name?:"", person.birthday?:"", person.deathday?:"", person.place_of_birth?:"", userVM)
            }
            Text(text = stringResource(R.string.biograf_a))
            Text(text = person.biography?:"")
            //SerieHeader(serie.backdrop_path)
        }
    }
}