package com.example.moviesaandseries.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.example.moviesaandseries.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)


// ...

val fontName = GoogleFont("Poppins")
val fontNameLato = GoogleFont("Lato")
val fontNameN = GoogleFont("nunito_sans")
val fontNameR = GoogleFont("merriweather")


val fontFamily = FontFamily(
    Font(googleFont = fontName, fontProvider = provider)
)

val fontFamilyLato = FontFamily(
    Font(googleFont = fontNameLato, fontProvider = provider)
)

val fontFamilyN = FontFamily(
    Font(googleFont = fontNameN, fontProvider = provider)

)

val fontFamilyR = FontFamily(
    Font(googleFont = fontNameR, fontProvider = provider)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)