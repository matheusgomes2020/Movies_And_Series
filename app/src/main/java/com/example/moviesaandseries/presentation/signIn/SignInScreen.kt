package com.example.moviesaandseries.presentation.signIn

import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviesaandseries.R
import com.example.moviesaandseries.presentation.general.DpDimensions
import com.example.moviesaandseries.ui.theme.BlueGrey11
import com.example.moviesaandseries.ui.theme.fontFamily3

@Composable
fun SignInScreen(
    state: SignInState,
    onSignInClick: () -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(color = if (isSystemInDarkTheme()) BlueGrey11 else Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(

            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                //.background(color = Color.Transparent),
        ) {


            Image(painterResource(id  = R.drawable.logo), contentDescription = "app logo",
                modifier = Modifier.size(200.dp))
            Spacer(modifier = Modifier.height(160.dp))
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp)),
               // .background(color = Color.White),
        ) {
            Row(
                modifier = Modifier
                    .clickable { onSignInClick() }
                    //.padding(10.dp)
                    .border(
                        border = BorderStroke(1.dp, Color.LightGray),
                        shape = RoundedCornerShape(16.dp),
                    )
                .background(color = Color.White)
                    .clip(RoundedCornerShape(16.dp)),
                   // .background(color = if (isSystemInDarkTheme())  Color.White else BlueGrey11),

                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
                ,) {
                Image(painterResource(id  = R.drawable.googlelogo), contentDescription = "google logo",
                    modifier = Modifier
                        .size(35.dp)
                        .padding(start = 10.dp,
                            top = 7.dp,
                            bottom = 7.dp))
                Text(text = "Login com o Google",
                    modifier = Modifier
                        .padding( vertical = 7.dp, horizontal = 10.dp ),
                    fontSize = 16.sp,
                    fontFamily = fontFamily3,
                    color = Color.DarkGray)
            }
        }
        }
    }
}

@Preview
@Composable
fun SignInScreen2(
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Image(painterResource(id  = R.drawable.logo), contentDescription = "app logo",
                modifier = Modifier.size(200.dp))
            Spacer(modifier = Modifier.height(160.dp))
            SocialAccountButton(label = "Login com o Google",
                image = R.drawable.googlelogo,
                modifier = Modifier.fillMaxWidth(),
                onButtonClick = { }
            )
//                Row(
//                    modifier = Modifier
//                        .padding(10.dp)
//                        .border(
//                            border = BorderStroke(1.dp, Color.LightGray),
//                            shape = RoundedCornerShape(16.dp)
//                        ),
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.SpaceBetween
//                    ,) {
//                    Image(painterResource(id  = R.drawable.googlelogo), contentDescription = "google logo",
//                        modifier = Modifier
//                            .size(35.dp)
//                            .padding(
//                                start = 10.dp,
//                                top = 7.dp,
//                                bottom = 7.dp
//                            ))
//                    Text(text = "Login com o Google",
//                        modifier = Modifier
//                            .padding( vertical = 7.dp, horizontal = 10.dp ),
//                        fontSize = 16.sp,
//                        fontFamily = fontFamily3,
//                        color = Color.DarkGray)
//                }
        }
    }


}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SocialAccountButton(
    label: String,
    @DrawableRes image: Int,
    onButtonClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {

    Surface(
        modifier = modifier
            .padding(16.dp),
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outline),
        shape = RoundedCornerShape(DpDimensions.Small),
        //color = MaterialTheme.colorScheme.background,
        onClick = onButtonClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = DpDimensions.Dp20,
                    vertical = DpDimensions.Small
                )
        ) {

            Image(
                painter = painterResource(id = image), contentDescription = "$label icon",
                modifier = Modifier.size(DpDimensions.Dp24)
            )

            Text(
                text = label, style = MaterialTheme.typography.titleMedium,
                //color = MaterialTheme.colorScheme.inversePrimary,
                modifier = Modifier.padding(start = DpDimensions.Small)
            )

        }
    }

}