package com.example.gestordepeliculas.ui.elements._common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.gestordepeliculas.R

@Composable
fun TextFieldPassword(
    passValue: String,
    onPassChange: (String) -> Unit,
    label: String = "",
    placeholder: String = "",
    supportingText: String = "",
    isError: Boolean = false,
    showPass: Boolean = false,
    onShowPassChange: () -> Unit,
){
    OutlinedTextField(
        value = passValue,
        onValueChange = onPassChange,
        label = { Text(text =  label) },
        placeholder = { Text(text = placeholder) },
        trailingIcon = {
            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onShowPassChange ) {
                    if(showPass){
                        Icon(painter = painterResource(id = R.drawable.ic_visibility_eye_on), modifier = Modifier.width(20.dp), contentDescription = stringResource(R.string.cd_ic_show_pass))
                    }else{
                        Icon(painter = painterResource(id = R.drawable.ic_visibility_eye_off), modifier = Modifier.width(20.dp), contentDescription = stringResource(R.string.cd_ic_show_pass))
                    }
                }
                if (isError) {
                    Icon(painter = painterResource(id = R.drawable.ic_exclamation), modifier = Modifier.width(20.dp), contentDescription = stringResource(R.string.cd_ic_error), tint = MaterialTheme.colorScheme.error)
                }
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
        visualTransformation = if(!showPass) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = if(!showPass) KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions.Default,
        )
}