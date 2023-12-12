package com.example.gestordepeliculas.ui.elements.details.person

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gestordepeliculas.ui.state.user.UserViewModel

@Composable
fun DetailsPerson(
    idPerson: String,
    name: String,
    birthday: String,
    deathday: String,
    place_of_birth: String,
    userVM: UserViewModel
) {
    Column(
        modifier = Modifier.padding(start = 5.dp)
    ) {
        Text(text = name)
        Text(text = birthday)
        Text(text = deathday)
        Text(text = place_of_birth)
    }
}