package com.example.gestordepeliculas.ui.elements.download

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.gestordepeliculas.domain.model.movieserie.movieseriepersonModel
import com.example.gestordepeliculas.ui.elements.scaffolds.tabscaffold.TabItems
import com.example.gestordepeliculas.ui.nav.Destination

@Composable
fun DownloadItem(
    movieserieperson: movieseriepersonModel,
    tabItems: TabItems,
    navController: NavController,
) {
    var isFocused by rememberSaveable() { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(168.dp)
            .border(
                width = if (isFocused) {
                    5.dp
                } else {
                    1.dp
                },
                color = Color.Black,
                shape = RoundedCornerShape(8.dp)
            )
            .onFocusChanged { isFocused = it.isFocused }
            .clickable {
                navController.navigate(
                    Destination.Details_Screen.withArgs(
                        "${movieserieperson.id}",
                        tabItems.getDisplayName()
                    )
                )
            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500${movieserieperson.photo_path}",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(112.dp)
                    .height(168.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(8.dp)
                    ),
            )
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = movieserieperson.title_name ?: "Undefined",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                )
            }
        }

    }
}