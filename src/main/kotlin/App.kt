import kotlinx.browser.window
import kotlinx.coroutines.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.h3
import react.useEffectOnce
import react.useState

val App = FC<Props> {
    var currentVideo: Video? by useState(null)
    var unwatchedVideos: List<Video> by useState(
        emptyList()
    )
    var watchedVideos: List<Video> by useState(
        emptyList()
    )

    useEffectOnce {
        MainScope().launch {
            unwatchedVideos = fetchVideos()
        }
    }

    h1 {
        +"KotlinConf Explorer"
    }
    div {
        h3 {
            +"Videos to watch"
        }
        VideoList {
            videos = unwatchedVideos
            selectedVideo = currentVideo
            onSelectedVideo = { video ->
                currentVideo = video
            }
        }
        h3 {
            +"Videos watched"
        }
        VideoList {
            videos = watchedVideos
            selectedVideo = currentVideo
            onSelectedVideo = { video ->
                currentVideo = video
            }
        }
    }
    div {
        currentVideo?.let { curr ->
            VideoPlayer {
                video = curr
                unwatchedVideo = curr in unwatchedVideos
                onWatchedButtonPressed = {
                    if (video in unwatchedVideos) {
                        unwatchedVideos = unwatchedVideos - video
                        watchedVideos = watchedVideos + video
                    } else {
                        watchedVideos = watchedVideos - video
                        unwatchedVideos = unwatchedVideos + video
                    }
                }
            }
        }
    }
}

suspend fun fetchVideo(id: Int): Video {
    val response = window
        .fetch("https://my-json-server.typicode.com/kotlin-hands-on/kotlinconf-json/videos/$id")
        .await()
        .text()
        .await()
    return Json.decodeFromString(response)
}

suspend fun fetchVideos(): List<Video> = coroutineScope {
    (1..25).map { id ->
        async {
            fetchVideo(id = id)
        }
    }.awaitAll()
}