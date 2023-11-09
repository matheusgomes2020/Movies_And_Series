package com.popcine.moviesaandseries.presentation.general

import androidx.annotation.NonNull
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun Player(urlVideo: String) {
    val activityLifecycle = LocalLifecycleOwner.current
    val context = LocalContext.current

    val youtubePlayer = remember {
        YouTubePlayerView(context).apply {
            activityLifecycle.lifecycle.addObserver(this)
            enableAutomaticInitialization = false
            initialize(object : AbstractYouTubePlayerListener() {
                override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo(urlVideo, 0f)
                }
            })
        }
    }

    DisposableEffect(
        AndroidView(
            modifier = Modifier.fillMaxWidth()
                .clip(shape = RoundedCornerShape(10.dp)),
            factory = { youtubePlayer }
        ),
        effect = { onDispose { youtubePlayer.release() } }
    )

//    AndroidView(
//        {
//            youtubePlayer
//        }, modifier = Modifier
//            .fillMaxWidth()
//            .wrapContentHeight()
//    )

}

//@Composable
//fun YouTubeScreen(
//    videoId: String,
//    modifier: Modifier = Modifier,
//) {
//    val lifecycleOwner = LocalLifecycleOwner.current
//    var youtubePlayerView: YouTubePlayerView? by remember { mutableStateOf(null) }
//    var player: YouTubePlayer? by remember { mutableStateOf(null) }
//    var fullscreenViewContainer: FrameLayout? by remember { mutableStateOf(null) }
//    AndroidView(
//        factory = { context ->
//            val fsView = LayoutInflater.from(context).inflate(R.layout.fullscreen, null, false)
//            fullscreenViewContainer = fsView.findViewById(R.id.full_screen_view_container)
//
//            val view = YouTubePlayerView(context).apply {
//                val self = this
//                enableAutomaticInitialization = false
//                addFullscreenListener(object : FullscreenListener {
//                    override fun onEnterFullscreen(
//                        fullscreenView: View,
//                        exitFullscreen: () -> Unit
//                    ) {
//                        self.visibility = View.GONE
//                        fullscreenViewContainer?.let {
//                            it.visibility = View.VISIBLE
//                            it.addView(fullscreenView)
//                        }
//                    }
//
//                    override fun onExitFullscreen() {
//                        self.visibility = View.VISIBLE
//                        fullscreenViewContainer?.let {
//                            visibility = View.GONE
//                            removeAllViews()
//                        }
//                    }
//                })
//
//                initialize(
//                    youTubePlayerListener = object : AbstractYouTubePlayerListener() {
//                        override fun onReady(youTubePlayer: YouTubePlayer) {
//                            player = youTubePlayer
//                            super.onReady(youTubePlayer)
//                            youTubePlayer.cueVideo(videoId, 0f)
//                        }
//                    },
//                    playerOptions = IFramePlayerOptions
//                        .Builder()
//                        .controls(1)
//                        .autoplay(0)
//                        .fullscreen(1)
//                        .build()
//                )
//                lifecycleOwner.lifecycle.addObserver(this)
//            }
//            youtubePlayerView = view
//            view
//        },
//        modifier = modifier,
//        update = {},
//    )
//    DisposableEffect(Unit) {
//        onDispose {
//            player?.pause()
//            youtubePlayerView?.let {
//                it.release()
//                lifecycleOwner.lifecycle.removeObserver(it)
//            }
//        }
//    }
//}