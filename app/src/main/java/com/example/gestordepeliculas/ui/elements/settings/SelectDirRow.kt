package com.example.gestordepeliculas.ui.elements.settings

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gestordepeliculas.ui.state.user.UserViewModel

@Composable
fun SelectDirRow(userVM: UserViewModel) {
    var context = LocalContext.current

    val openDocumentTreeLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocumentTree() ,
        onResult = { uri ->
            Log.d("DebugTag", "Debug on: Com.SelectDirRow,\n  ${uri.toString()}")
            userVM.setDirMovieSerie(uri.toString())
        },
    )

    Column {
        Row {
            Text(text = "Ruta Peli: ")
            Text(text = "${userVM.dirMovieSerie}")
        }
        Button(
            onClick = {
                openDocumentTreeLauncher.launch(null)
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Cambiar",
                fontSize = 16.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
    }

}
