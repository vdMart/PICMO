package com.example.gestordepeliculas.ui.elements.search.searchbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.gestordepeliculas.R
import com.example.gestordepeliculas.ui.state.movie.MovieSerieViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarHeader(
    navController: NavController,
    movieSerieVM: MovieSerieViewModel,
    query: String,
    queryOnChange: (String) -> Unit,
    active: Boolean,
    activeOnChange: (Boolean) -> Unit,
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            query = query,
            onQueryChange = { queryOnChange(it) },
            onSearch = { activeOnChange(false) },
            active = active,
            onActiveChange = { activeOnChange(it) },
            placeholder = { Text(text = stringResource(R.string.searchbar_palceholder)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.cd_ic_icon_search)
                )
            },
            trailingIcon = {
                if (active) {
                    Icon(
                        modifier = Modifier.clickable {
                            if (query.isNotEmpty()) {
                                queryOnChange("")
                            } else {
                                activeOnChange(false)
                            }
                        },
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.cd_ic_icon_cancel),
                    )
                }
            }
        ) {
            SearchBarContent(navController, movieSerieVM, query)
        }
    }

}