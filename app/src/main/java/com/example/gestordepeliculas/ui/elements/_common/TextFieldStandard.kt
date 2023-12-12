package com.example.gestordepeliculas.ui.elements._common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.gestordepeliculas.R

@Composable
fun TextFieldStandard(
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "",
    placeholder: String = "",
    supportingText: String = "",
    isError: Boolean = false,
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
        trailingIcon = {
            if (isError) {
                Icon(painter = painterResource(id = R.drawable.ic_exclamation), modifier = Modifier.width(20.dp), contentDescription = "error", tint = MaterialTheme.colorScheme.error)
            }
        },
        supportingText = {
            if (isError) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = supportingText,
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        isError = isError,
    )
}
