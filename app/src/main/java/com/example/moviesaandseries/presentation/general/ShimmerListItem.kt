package com.example.moviesaandseries.presentation.general

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ShimmerListItem(
    isLoading: Boolean,
    contentAfterLoading: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    if ( isLoading ) {
        Row {
            Column {

            }
            Box(
                modifier = Modifier
                    .width(110.dp)
                    .height(150.dp)
                    .clip(shape = RoundedCornerShape(15.dp))
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.width( 16.dp ))
            Column(
                modifier = Modifier.weight( 1f )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                        .shimmerEffect()
                )
                Spacer(modifier = Modifier.height( 16.dp ))
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(20.dp)
                        .shimmerEffect()
                )
            }
        }
    } else {
        contentAfterLoading()
    }
}

@Composable
fun ShimmerListItemMovieDetails(
    isLoading: Boolean,
    contentAfterLoading: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    if ( isLoading ) {
            Column(
                modifier = Modifier.padding(
                    15.dp
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                        .shimmerEffect()
                )
                Spacer(modifier = Modifier.height( 16.dp ) )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier
                        .size(25.dp)
                        .shimmerEffect())
                    //Spacer(modifier = Modifier.width( 16.dp ) )
                    Box(modifier = Modifier
                        .height(25.dp)
                        .width(70.dp)
                        .shimmerEffect())
                    //Spacer(modifier = Modifier.width( 16.dp ) )
                    Box(modifier = Modifier
                        .size(25.dp)
                        .shimmerEffect())
                   // Spacer(modifier = Modifier.width( 16.dp ) )
                    Box(modifier = Modifier
                        .height(25.dp)
                        .width(70.dp)
                        .shimmerEffect())
                    //Spacer(modifier = Modifier.width( 16.dp ) )
                    Box(modifier = Modifier
                        .size(25.dp)
                        .shimmerEffect())
                    //Spacer(modifier = Modifier.width( 16.dp ) )
                    Box(modifier = Modifier
                        .height(25.dp)
                        .width(70.dp)
                        .shimmerEffect())
                }
                Spacer(modifier = Modifier.height( 16.dp ) )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .shimmerEffect()
                )
                Spacer(modifier = Modifier.height( 16.dp ) )
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp)
                            .shimmerEffect()
                    )
                    Spacer(modifier = Modifier.height( 5.dp ) )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp)
                            .shimmerEffect()
                    )
                    Spacer(modifier = Modifier.height( 5.dp ) )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp)
                            .shimmerEffect()
                    )
                }
                Spacer(modifier = Modifier.height( 16.dp ) )
                Column(
                    Modifier.padding(horizontal = 5.dp)
                ) {
                    Text(
                        text = "Elenco",
                        style = MaterialTheme.typography.headlineMedium,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    LazyRow(contentPadding = PaddingValues( 5.dp )) {
                        items(20) {
                            Box(
                                modifier = Modifier
                                    .width(110.dp)
                                    .height(150.dp)
                                    .clip(shape = RoundedCornerShape(15.dp))
                                    .shimmerEffect()
                            )
                            Spacer(modifier = Modifier.width( 16.dp ))
                        }
                    }
                }



            }



    } else {
        contentAfterLoading()
    }
}

fun Modifier.shimmerEffect(): Modifier = composed { 
    var size by remember {
        mutableStateOf( IntSize.Zero )
    }
    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween( 1000 )
        )
    )
    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFB8B8B5),
                Color(0xFF8F8B8B),
                Color(0xFFB8B8B5)
            ),
            start = Offset( startOffsetX, 0f ),
            end = Offset( startOffsetX + size.width.toFloat(), size.height.toFloat() )
        )
    )

        .onGloballyPositioned {
            size =  it.size
        }
}