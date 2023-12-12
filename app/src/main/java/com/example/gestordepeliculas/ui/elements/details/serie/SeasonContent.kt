package com.example.gestordepeliculas.ui.elements.details.serie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gestordepeliculas.R
import com.example.gestordepeliculas.domain.model.movieserie.serie.SeasonModel
import com.example.gestordepeliculas.domain.model.user.UserModel
import com.example.gestordepeliculas.ui.elements.scaffolds.tabscaffold.TabItems
import com.example.gestordepeliculas.ui.elements.videoplayer.VideoViewModel
import com.example.gestordepeliculas.ui.state.user.UserViewModel

@Composable
fun SeasonContent(
    idSerie: String,
    typeModel: TabItems,
    seasons: List<SeasonModel>,
    userModelState: UserModel,
    videoVM: VideoViewModel,
    userVM: UserViewModel,
    navController: NavController
) {

    Spacer(modifier = Modifier.height(10.dp))

    Text(
        text = stringResource(R.string.temporadas),
        fontWeight = FontWeight.Bold,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )

    LazyColumn(
        modifier = Modifier.height(400.dp)
    ) {
        items(items = seasons) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(text = "${it.name}", fontWeight = FontWeight.Bold)
                    Text(text = stringResource(R.string.total_episodes) + "${it.episode_count}")
                }
                Text(text = "${it.overview}")

                LazyRow(
                    modifier = Modifier.fillMaxWidth().padding(5.dp))
                {
                    items(it.episode_count ?: 0) {
                        Column(modifier = Modifier.padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            EpisodeContent(idSerie, typeModel, it, userModelState, videoVM, userVM, navController)
                        }
                    }
                }

            }
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}