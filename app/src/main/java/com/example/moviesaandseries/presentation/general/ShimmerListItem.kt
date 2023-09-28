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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
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
fun ShimmerDetail(
    isLoading: Boolean,
    contentAfterLoading: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    if ( isLoading ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 15.dp)
        ) {
            Title()
            Spacer(modifier = Modifier.height(16.dp))
            TopItems( count = 3 )
            Spacer(modifier = Modifier.height(16.dp))
            Genres( count = 3 )
            Spacer(modifier = Modifier.height(16.dp))
            Image()
            Spacer(modifier = Modifier.height(16.dp))
            OverviewAndBiography( count = 5 )
            Spacer(modifier = Modifier.height(11.dp))
            RowCards()
            Spacer(modifier = Modifier.height(16.dp))
            Crew()
        }

    } else {
        contentAfterLoading()
    }
}

@Composable
fun SeasonDetailShimmer(
    isLoading: Boolean,
    contentAfterLoading: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    if ( isLoading ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 15.dp)
        ) {
            Title()
            Spacer(modifier = Modifier.height(16.dp))
            ImageSeason()
            Spacer(modifier = Modifier.height(16.dp))
            OverviewAndBiography(count = 3)
            Spacer(modifier = Modifier.height(16.dp))
            Episodes()
        }

    } else {
        contentAfterLoading()
    }
}

@Composable
fun EpisodeDetailShimmer(
    isLoading: Boolean,
    contentAfterLoading: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    if ( isLoading ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 15.dp)
        ) {
            Title()
            Spacer(modifier = Modifier.height(16.dp))
            OverviewAndBiography(count = 3)
            Spacer(modifier = Modifier.height(16.dp))
            RowCards()
            Spacer(modifier = Modifier.height(16.dp))
            Crew()
        }

    } else {
        contentAfterLoading()
    }
}

@Composable
fun ActorDetailShimmer(
    isLoading: Boolean,
    contentAfterLoading: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    if ( isLoading ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 15.dp)
        ) {
            Title()
            Spacer(modifier = Modifier.height(16.dp))
            TopItems( count = 2 )
            Spacer(modifier = Modifier.height(16.dp))
            OverviewAndBiography( count = 5 )
            Spacer(modifier = Modifier.height(16.dp))
            ImagesActor()
            Spacer(modifier = Modifier.height(16.dp))
            RowCards()
            Spacer(modifier = Modifier.height(16.dp))
            RowCards()
        }

    } else {
        contentAfterLoading()
    }
}

@Composable
private fun Genres( count: Int ) {
    LazyRow() {
        items( count ) {
            Box(
                modifier = Modifier
                    .height(15.dp)
                    .width(60.dp)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}

@Composable
private fun Image() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .shimmerEffect()
    )
}

@Composable
private fun ImageSeason() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .shimmerEffect()
    )
}

@Composable
private fun ImageActor() {
    Box(
        modifier = Modifier
            .width(150.dp)
            .height(200.dp)
            .clip(shape = RoundedCornerShape(15.dp))
            .shimmerEffect()
    )
}

@Composable
private fun ImagesActor() {
    Column(
    ) {
        Box(
            modifier = Modifier
                .width(100.dp)
                .height(25.dp)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(15.dp))
        LazyRow(contentPadding = PaddingValues(5.dp)) {
            items(3) {
                ImageActor()
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }
}

@Composable
private fun Title() {
    Spacer(modifier = Modifier.height(10.dp))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .shimmerEffect()
    )
}

@Composable
private fun OverviewAndBiography( count: Int ) {
    LazyColumn(
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        items( count ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .shimmerEffect()
            )
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}

@Composable
private fun TopItems( count: Int ) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items( count ) {
            Row {
                Box(
                    modifier = Modifier
                        .size(25.dp)
                        .shimmerEffect()
                )
                Spacer(modifier = Modifier.width(10.dp))
                Box(
                    modifier = Modifier
                        .height(25.dp)
                        .width(70.dp)
                        .shimmerEffect()
                )
            }
        }
    }
}

@Composable
private fun RowCards() {
    Column(
    ) {
        Box(
            modifier = Modifier
                .width(100.dp)
                .height(25.dp)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(15.dp))
        LazyRow(contentPadding = PaddingValues(5.dp)) {
            items(3) {
                Box(
                    modifier = Modifier
                        .width(110.dp)
                        .height(150.dp)
                        .clip(shape = RoundedCornerShape(15.dp))
                        .shimmerEffect()
                )
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }
}



@Composable
 fun SearchResultsShimmer(
    isLoading: Boolean,
    contentAfterLoading: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    if (isLoading) {

            Row(
                modifier = Modifier.padding(
                    bottom = 3.dp
                )
            ) {
                Box(
                    modifier = Modifier
                        .size(110.dp)
                        .clip(shape = RoundedCornerShape(15.dp))
                        .shimmerEffect()
                )
                Column(
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 10.dp, start = 15.dp, end = 10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .width(110.dp)
                            .height(13.dp)
                            .shimmerEffect()
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Box(
                        modifier = Modifier
                            .width(110.dp)
                            .height(13.dp)
                            .shimmerEffect()
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp)
                            .shimmerEffect()
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp)
                            .shimmerEffect()
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp)
                            .shimmerEffect()
                    )
                }

            }


    }
    else {
        contentAfterLoading()
    }

}
@Composable
private fun Episodes() {
    Column(
    ) {
        Box(
            modifier = Modifier
                .width(110.dp)
                .height(23.dp)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(15.dp))
        LazyColumn() {
            items(5) {
                Row(
                    modifier = Modifier
                        .padding(
                            10.dp
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .size(110.dp)
                            .clip(shape = RoundedCornerShape(15.dp))
                            .shimmerEffect()
                    )
                    Column(
                        modifier = Modifier.padding(
                            10.dp
                            )
                    ) {
                        Box(
                            modifier = Modifier
                                .width(150.dp)
                                .height(18.dp)
                                .shimmerEffect()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(13.dp)
                                .shimmerEffect()
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(13.dp)
                                .shimmerEffect()
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(13.dp)
                                .shimmerEffect()
                        )
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }
}

@Composable
private fun Crew() {
    Column(
    ) {
        Box(
            modifier = Modifier
                .width(100.dp)
                .height(23.dp)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .width(90.dp)
                .height(20.dp)
                .padding(start = 5.dp)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .width(100.dp)
                .height(23.dp)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn(contentPadding = PaddingValues(5.dp)) {
            items(3) {
                Box(
                    modifier = Modifier
                        .width(90.dp)
                        .height(20.dp)
                        .padding(start = 5.dp)
                        .shimmerEffect()
                )
                Spacer(modifier = Modifier.height(5.dp))
            }
        }
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