package com.example.gestordepeliculas.ui.elements.login

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
import androidx.compose.ui.text.AnnotatedString
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
import com.example.gestordepeliculas.ui.state.login.LoginEvent
import com.example.gestordepeliculas.ui.state.login.LoginUiState
import com.example.gestordepeliculas.ui.state.login.LoginViewModel

@Composable
fun LoginBody(
    uiState: State<LoginUiState>,
    loginVM: LoginViewModel,
    loginState: State<StateVM>,
    navController: NavController,
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

            if (loginState.value.errorException != null) {
                Text(text = "${loginState.value.errorException?.message}", color = Color.Red)
            }

            TextFieldStandard(
                value = uiState.value.emailState,
                onValueChange = { loginVM.onEvent(LoginEvent.EnteredEmail(it)) },
                label = stringResource(R.string.label_email),
                placeholder = stringResource(R.string.placeholder_email),
                isError = false,
            )
            Spacer(Modifier.height(5.dp))

            TextFieldPassword(
                passValue = uiState.value.passwordState,
                onPassChange = { loginVM.onEvent(LoginEvent.EnteredPassword(it)) },
                label = stringResource(R.string.label_pass),
                placeholder = stringResource(R.string.placeholder_pass),
                isError = false,
                showPass = uiState.value.passwordVisibilityState,
                onShowPassChange = { loginVM.onEvent(LoginEvent.TogglePasswordVisibility) },
            )

            if (loginState.value.errorException != null) {
                TextButton(
                    onClick = {
                        focusManager.clearFocus()
                        loginVM.onEvent(LoginEvent.SendPasswordResetEmail)
                    },
                ) {
                    Text(text = AnnotatedString(stringResource(R.string.forget_pass)))
                }
            }

            Spacer(Modifier.height(15.dp))

            Button(
                onClick = {
                    if (!loginState.value.isLoading) {
                        focusManager.clearFocus()
                        loginVM.onEvent(LoginEvent.LoginWithEmailAndPassword)
                    }
                },
                enabled = true,/*TODO:*/
            ) {
                Text(text = stringResource(R.string.loggin_btn))
            }
            Spacer(Modifier.height(15.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = stringResource(R.string.nothave_account))
                TextButton(onClick = {
                    focusManager.clearFocus()
                    navController.navigate(Destination.Register_Screen.route)
                }) {
                    Text(text = stringResource(R.string.create_account_nav))
                }
            }

            Spacer(modifier = Modifier.height(25.dp))
            Text(text = stringResource(R.string.or_also), style = TextStyle(color = Color.Gray))
            Spacer(modifier = Modifier.height(25.dp))

            BtnGoogle(
                googleSignInAccountFromIntent = {
                    focusManager.clearFocus()
                    loginVM.onEvent(LoginEvent.LoginWithGoogleCredential(it))
                }
            )

            BtnSignInAnonymous(
                signInAnonymously = {
                    focusManager.clearFocus()
                    loginVM.onEvent(LoginEvent.SignInAnonymously)
                }
            )

        }

    }

}