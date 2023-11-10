package com.popcine.moviesaandseries.presentation.sign_up
import android.content.Context
import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.popcine.moviesaandseries.R
import com.popcine.moviesaandseries.common.navigation.AppGraph
import com.popcine.moviesaandseries.presentation.general.DpDimensions
import com.popcine.moviesaandseries.presentation.signIn.AuthViewModel
import com.popcine.moviesaandseries.presentation.signIn.Resource
import com.popcine.moviesaandseries.ui.theme.BlueGrey11
import com.popcine.moviesaandseries.ui.theme.MoviesAandSeriesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(viewModel: AuthViewModel?, navController: NavController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
 
    val authResource = viewModel?.signupFlow?.collectAsState()
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = if (isSystemInDarkTheme()) BlueGrey11 else Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
            //.background(color = Color.Transparent),
        ) {
            Image(
                painterResource(id  = R.drawable.logo), contentDescription = "app logo",
                modifier = Modifier.size(70.dp))
            Spacer(modifier = Modifier.height(40.dp))
            Text(text = "Create a new account!",
                style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Create your account!",
                style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(40.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Name *",
                    style = MaterialTheme.typography.headlineSmall,
                )
                TextField(
                    value = name,
                    onValueChange = {
                        name = it
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    placeholder = {
                        Text(
                            text = "Type your name",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Magenta,
                        focusedIndicatorColor = Color.Gray,
                        unfocusedIndicatorColor = Color.Gray,
                        disabledIndicatorColor = Color.Red,
                        cursorColor = MaterialTheme.colorScheme.onPrimary
                    ),                keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = false,
                        keyboardType = KeyboardType.Email,
                        imeAction = androidx.compose.ui.text.input.ImeAction.Next
                    )
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "E-mail *",
                    style = MaterialTheme.typography.headlineSmall,
                )
                TextField(
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
//                        label = {Text(text = "Senha") },
                    placeholder = {
                        Text(
                            text = "Type your e-mail",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Magenta,
                        focusedIndicatorColor = Color.Gray,
                        unfocusedIndicatorColor = Color.Gray,
                        disabledIndicatorColor = Color.Red,
                        cursorColor = MaterialTheme.colorScheme.onPrimary
                    ),                keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = false,
                        keyboardType = KeyboardType.Email,
                        imeAction = androidx.compose.ui.text.input.ImeAction.Next
                    )
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column {
                Text(
                    text = "Password *",
                    style = MaterialTheme.typography.headlineSmall,
                )
                TextField(
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    placeholder = {
                        Text(
                            text = "Crete a password with 6 characters",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Magenta,
                        focusedIndicatorColor = Color.Gray,
                        unfocusedIndicatorColor = Color.Gray,
                        disabledIndicatorColor = Color.Red,
                        cursorColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = false,
                        keyboardType = KeyboardType.Password,
                        imeAction = androidx.compose.ui.text.input.ImeAction.Done
                    )
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                shape = RoundedCornerShape(DpDimensions.Normal),
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    viewModel?.signupUser(name, email, password)
                },
            ) {
                Text(text = "Sign Up", style = MaterialTheme.typography.titleMedium)
            }
            privacyAndTerms(context, "Sign Up")

            val annotatedTextSignIn = buildAnnotatedString {
                append("Do you have an account? ")
                withStyle(style = SpanStyle(
                    fontWeight = FontWeight.Bold)
                ) {
                    append("Sign In") } }
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                modifier = Modifier
                    .clickable {
                        navController.navigate(AppGraph.auth.LOGIN)
                    },
                text = annotatedTextSignIn,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface
            )
            authResource?.value?.let {
                when (it) {
                    is Resource.Failure -> {
                        Toast.makeText(context, it.exception.message, Toast.LENGTH_SHORT).show()
                    }

                    is Resource.Loading -> {
                        CircularProgressIndicator()
                    }

                    is Resource.Success -> {
                        LaunchedEffect(Unit) {
                            navController.navigate(AppGraph.auth.FORGOT_PASSWORD)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun privacyAndTerms(
    context: Context,
    text: String
) {

    val webIntentPrivacy: Intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://doc-hosting.flycricket.io/pop-cine-privacy-policy/7016f44d-67a3-40d5-8700-868e80fab0d1/privacy"))
    val webIntentTerms: Intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://doc-hosting.flycricket.io/pop-cine-terms-of-use/39ce1a2c-e7a5-4eb2-ab3e-4d86affd2585/terms"))

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp)
    ) {
        Row {
            Text(
                text = "Clicking $text you are agreeing if our ",
                fontSize = 14.sp
            )
            Text(
                modifier = Modifier
                    .clickable {
                        context.startActivity(webIntentTerms)
                    },
                text = "Terms of Use ",
                textDecoration = TextDecoration.Underline,
                fontSize = 14.sp
            )
        }
        Row {
            Text(
                text = "and ",
                fontSize = 14.sp
            )
            Text(
                modifier = Modifier
                    .clickable {
                        context.startActivity(webIntentPrivacy)
                    }, text = "Privacy Policy", textDecoration = TextDecoration.Underline,
                fontSize = 14.sp
            )
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun SignupScreenPreviewLight() {
    MoviesAandSeriesTheme {
        SignupScreen(null, rememberNavController())
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SignupScreenPreviewDark() {
    MoviesAandSeriesTheme {
        SignupScreen(null, rememberNavController())
    }
}