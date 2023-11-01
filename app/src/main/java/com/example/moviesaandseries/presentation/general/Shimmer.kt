package com.example.moviesaandseries.presentation.general

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.moviesaandseries.ui.theme.BlueGrey11

@Preview
@Composable
fun ShimmerTrending(){
    Box(
        modifier = Modifier
            .width(370.dp)
            .height(190.dp)
            .clip(shape = RoundedCornerShape(DpDimensions.Dp20))
            .shimmerEffect()
    )
}

@Preview
@Composable
fun ShimmerMovieAndSeriesListItem(){
    Box(
        modifier = Modifier
            .width(120.dp)
            .height(170.dp)
            .clip(shape = RoundedCornerShape(DpDimensions.Dp20))
            .shimmerEffect()
    )
}
@Composable
fun ShimmerListItem(
    isLoading: Boolean,
    contentAfterLoading: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    if ( isLoading ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 5.dp)
        ) {

                Column(
                    modifier = Modifier
                        //.padding(5.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .width(110.dp)
                            .height(150.dp)
                            .clip(shape = RoundedCornerShape(15.dp))
                            .shimmerEffect()
                    )
                    Spacer(modifier = Modifier.height( 5.dp ))
                    Box(
                        modifier = Modifier
                            .width(110.dp)
                            .height(18.dp)
                            .shimmerEffect()
                    )
                }
        }
    } else {
        contentAfterLoading()
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ShimmerDetail(
) {
        Column(
            modifier = Modifier
                //.padding(horizontal = 15.dp)
                .background(color = if (isSystemInDarkTheme()) BlueGrey11 else Color.White),
        ) {
            CustomPadding(verticalPadding = 0.dp, horizontalPadding = DpDimensions.Normal) {
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
                Crew(3)
            }
        }
    }


@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SeasonDetailShimmer(

) {
        Column(
            modifier = Modifier
                .background(color = if (isSystemInDarkTheme()) BlueGrey11 else Color.White),
        ) {
            CustomPadding(verticalPadding = 0.dp, horizontalPadding = DpDimensions.Normal) {
            Title()
            Spacer(modifier = Modifier.height(16.dp))
            ImageSeason()
            Spacer(modifier = Modifier.height(16.dp))
            OverviewAndBiography(count = 3)
            Spacer(modifier = Modifier.height(16.dp))
            EpisodeShimmer()
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun EpisodeDetailShimmer(
) {
        Column(
            modifier = Modifier
                .background(color = if (isSystemInDarkTheme()) BlueGrey11 else Color.White),
        ) {
            CustomPadding(verticalPadding = 0.dp, horizontalPadding = DpDimensions.Normal) {
                Title()
                Spacer(modifier = Modifier.height(10.dp))
                OverviewAndBiography(count = 2)
                Spacer(modifier = Modifier.height(10.dp))
                RowCast()
                Spacer(modifier = Modifier.height(10.dp))
                Crew(1)
                ImagesEpisode()
                Spacer(modifier = Modifier.height(500.dp))

            }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ActorDetailShimmer(
) {
        Column(
            modifier = Modifier
                .background(color = if (isSystemInDarkTheme()) BlueGrey11 else Color.White),
        ) {
            CustomPadding(verticalPadding = 0.dp, horizontalPadding = DpDimensions.Normal) {

                Title()
                Spacer(modifier = Modifier.height(10.dp))
                TopItems(count = 2)
                Spacer(modifier = Modifier.height(10.dp))
                OverviewAndBiography(count = 3)
                Spacer(modifier = Modifier.height(10.dp))
                ImagesActor()
                Spacer(modifier = Modifier.height(10.dp))
                RowCards()
                Spacer(modifier = Modifier.height(10.dp))
                RowCards()
            }
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
            .clip(shape = RoundedCornerShape(15.dp))
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
            .clip(shape = RoundedCornerShape(15.dp))
            .shimmerEffect()
    )
}

@Composable
private fun ImageEpisode() {
    Box(
        modifier = Modifier
            .width(355.dp)
            .height(200.dp)
            .clip(shape = RoundedCornerShape(15.dp))
            .shimmerEffect()
    )
}

@Composable
private fun ImagesEpisode() {
    Column(
    ) {
        Box(
            modifier = Modifier
                .width(100.dp)
                .height(25.dp)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(15.dp))
        LazyRow(contentPadding = PaddingValues()) {
            items(3) {
                ImageEpisode()
                Spacer(modifier = Modifier.width(10.dp))
            }
        }
    }
}

@Composable
private fun ImageActor() {
    Box(
        modifier = Modifier
            .width(165.dp)
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
        LazyRow(contentPadding = PaddingValues()) {
            items(3) {
                ImageActor()
                Spacer(modifier = Modifier.width(10.dp))
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
        LazyRow(contentPadding = PaddingValues(8.dp)) {
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
private fun RowCast() {
    Column(
    ) {
        Box(
            modifier = Modifier
                .width(100.dp)
                .height(25.dp)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow() {
            items(3) {
                Box(
                    modifier = Modifier
                        .width(110.dp)
                        .height(120.dp)
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

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun EpisodeShimmer() {
        LazyColumn() {
            items(5) {
                Row(
                    modifier = Modifier
                        .padding(
                            vertical = 3.dp
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

@Composable
private fun Crew(count: Int) {
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
            items(count) {
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

            colors = if (isSystemInDarkTheme()) {
                listOf(
                    Color(0xFF464643),
                    Color(0xFF3D3A3A),
                    Color(0xFF333332)
                )

            } else {
                listOf(
                    Color(0xFFC9C9C3),
                    Color(0xFFB8B5B5),
                    Color(0xFFCECECA)


                )
            },
            start = Offset( startOffsetX, 0f ),
            end = Offset( startOffsetX + size.width.toFloat(), size.height.toFloat() )
        )
    )

        .onGloballyPositioned {
            size =  it.size
        }
}