package com.example.gestordepeliculas.ui.elements.settings

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.gestordepeliculas.R
import com.example.gestordepeliculas.domain.model.user.UserThemes
import com.example.gestordepeliculas.ui.state.user.UserEvent
import com.example.gestordepeliculas.ui.state.user.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaDesplegable(userVM: UserViewModel) {
    val themeList = arrayOf("${UserThemes.SYSTEM}", "${UserThemes.LIGHT}", "${UserThemes.DARK}")
    var expanded by remember { mutableStateOf(false) }
    val userModel = userVM.userModelState.collectAsState()

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = stringResource(R.string.seleccionar_tema))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
        ) {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = {
                    expanded = !expanded
                }
            ) {
                TextField(
                    value = userModel.value.userDetails.theme.name,
                    onValueChange = { },
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.menuAnchor().focusable(false)
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    themeList.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = item) },
                            onClick = {
                                expanded = false
                                userVM.onEvent(UserEvent.ChangeTheme(UserThemes.valueOf(item)))
                            }
                        )
                    }
                }

            }
        }
    }

}