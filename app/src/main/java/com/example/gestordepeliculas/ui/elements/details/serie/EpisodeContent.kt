package com.example.gestordepeliculas.ui.elements.details.serie

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gestordepeliculas.domain.model.user.UserModel
import com.example.gestordepeliculas.ui.elements.details._common.VideoPlayer
import com.example.gestordepeliculas.ui.elements.details._common.VideoSelector
import com.example.gestordepeliculas.ui.elements.scaffolds.tabscaffold.TabItems
import com.example.gestordepeliculas.ui.elements.videoplayer.VideoViewModel
import com.example.gestordepeliculas.ui.state.user.UserViewModel

@Composable
fun EpisodeContent(
    idSerie: String,
    typeModel: TabItems,
    count: Int,
    userModelState: UserModel,
    videoVM: VideoViewModel,
    userVM: UserViewModel,
    navController: NavController
) {
    Card(
        modifier = Modifier.border(
            width = 2.dp,
            color = Color.Black,
            shape = RoundedCornerShape(8.dp)
        )
    ) {
        Column(modifier = Modifier.padding(2.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Episode ${count+1}")

            VideoSelector(
                idMovieSerie = idSerie,
                typeModel = typeModel,
                userModelState = userModelState,
                videoVM = videoVM,
                userVM = userVM,
            )
            VideoPlayer(
                idMovieSerie = idSerie,
                stringContent = userModelState.userDetails.movieFile,
                navController = navController,
                videoVM = videoVM,
            )
        }
    }
}