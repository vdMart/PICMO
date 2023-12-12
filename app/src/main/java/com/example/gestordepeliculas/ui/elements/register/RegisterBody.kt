package com.example.gestordepeliculas.ui.elements.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gestordepeliculas.R
import com.example.gestordepeliculas.ui.elements._common.BtnGoogle
import com.example.gestordepeliculas.ui.elements._common.BtnSignInAnonymous
import com.example.gestordepeliculas.ui.elements._common.TextFieldPassword
import com.example.gestordepeliculas.ui.elements._common.TextFieldStandard
import com.example.gestordepeliculas.ui.nav.Destination
import com.example.gestordepeliculas.ui.state.StateVM
import com.example.gestordepeliculas.ui.state.register.RegisterEvent
import com.example.gestordepeliculas.ui.state.register.RegisterUiState
import com.example.gestordepeliculas.ui.state.register.RegisterViewModel

@Composable
fun RegisterBody(
    registerVM: RegisterViewModel,
    registerState: State<StateVM>,
    uiState: State<RegisterUiState>,
    navController: NavController
) {
    val focusManager = LocalFocusManager.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_picmo_text)),
                    contentDescription = stringResource(R.string.cd_ic_logo_app),
                    modifier = Modifier.size(150.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                )
            }

            if (registerState.value.errorException != null) {
                Text(text = "${registerState.value.errorException?.message}", color = Color.Red)
            }

            TextFieldStandard(
                value = uiState.value.emailState,
                onValueChange = { registerVM.onEvent(RegisterEvent.EnteredEmail(it)) },
                label = stringResource(R.string.label_email),
                placeholder = stringResource(R.string.placeholder_email),
                supportingText = stringResource(R.string.supportingtext_email),
                isError = !uiState.value.isValidEmail && !uiState.value.emailState.isEmpty(),
            )
            Spacer(Modifier.height(5.dp))

            TextFieldPassword(
                passValue = uiState.value.passwordState,
                onPassChange = { registerVM.onEvent(RegisterEvent.EnteredPassword(it)) },
                label = stringResource(R.string.label_pass),
                placeholder = stringResource(R.string.placeholder_pass),
                supportingText = stringResource(R.string.supportingtext_pass),
                isError = !uiState.value.isValidPassword && !uiState.value.passwordState.isEmpty(),
                showPass = uiState.value.passwordVisibilityState,
                onShowPassChange = { registerVM.onEvent(RegisterEvent.TogglePasswordVisibility) },
            )
            Spacer(Modifier.height(5.dp))

            TextFieldPassword(
                passValue = uiState.value.repeatPasswordState,
                onPassChange = { registerVM.onEvent(RegisterEvent.EnteredRepeatPassword(it)) },
                label = stringResource(R.string.label_repeat_pass),
                placeholder = stringResource(R.string.placeholder_repeat_pass),
                supportingText = stringResource(R.string.supportingtext_repeat_pass),
                isError = !uiState.value.isValidRepeatPassword && !uiState.value.repeatPasswordState.isEmpty(),
                showPass = uiState.value.repeatPasswordVisibilityState,
                onShowPassChange = { registerVM.onEvent(RegisterEvent.ToggleRepeatPasswordVisibility) },
            )
            Spacer(Modifier.height(15.dp))

            Button(
                onClick = {
                    if (!registerState.value.isLoading) {
                        focusManager.clearFocus()
                        registerVM.onEvent(RegisterEvent.RegisterWithEmailAndPassword)
                    }
                },
                enabled = uiState.value.userValidated(),
            ) {
                Text(text = stringResource(R.string.loggin_btn))
            }
            Spacer(Modifier.height(15.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = stringResource(R.string.have_account))
                TextButton(onClick = {
                    focusManager.clearFocus()
                    navController.navigate(Destination.Login_Screen.route)
                }) {
                    Text(text = stringResource(R.string.loggin_btn_nav))
                }
            }

            Spacer(modifier = Modifier.height(25.dp))
            Text(text = stringResource(R.string.or_also), style = TextStyle(color = Color.Gray))
            Spacer(modifier = Modifier.height(25.dp))

            BtnGoogle(
                googleSignInAccountFromIntent = {
                    focusManager.clearFocus()
                    registerVM.onEvent(RegisterEvent.LoginWithGoogleCredential(it))
                }
            )

            BtnSignInAnonymous(
                signInAnonymously = {
                    focusManager.clearFocus()
                    registerVM.onEvent(RegisterEvent.SignInAnonymously)
                }
            )

        }

    }

}