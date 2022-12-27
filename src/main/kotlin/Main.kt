import kotlinx.browser.document
import react.create
import react.dom.client.createRoot

fun main() {
    val container = document.getElementById("root") ?: error("Couldn't find root container!")
    createRoot(container).render(App.create())
}

data class Video(
    val id: Int,
    val title: String,
    val speaker: String,
    val videoUrl: String
)
