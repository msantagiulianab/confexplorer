import react.FC
import react.Props
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.p
import react.key

external interface VideoListProps : Props {
    var videos: List<Video>
}

val VideoList = FC<VideoListProps> { props ->
    for (video in props.videos) {
        p {
            key = video.id.toString()
            +"${video.speaker}: ${video.title}"
        }
    }
}