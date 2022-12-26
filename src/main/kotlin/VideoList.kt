import kotlinx.browser.window
import react.FC
import react.Props
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.p
import react.key
import react.useState

external interface VideoListProps : Props {
    var videos: List<Video>
    var selectedVideo: Video?
}

val VideoList = FC<VideoListProps> { props ->
    for (video in props.videos) {
        p {
            key = video.id.toString()
            onClick = {
                props.selectedVideo = video
            }
            if (video == props.selectedVideo) {
                +"▶ "
            }
            +"${video.speaker}: ${video.title}"
        }
    }
}