import androidx.compose.desktop.Window
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.imageResource
import jdk.jshell.execution.Util
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

fun main() {
    Window(
        icon = getWindowIcon()
    ) {
        val image = imageResource("logo.png")
        Image(
            bitmap = image,
            modifier = Modifier.fillMaxSize()
        )
    }
}

fun getWindowIcon(): BufferedImage {
    val classLoader = Util::class.java.classLoader
    val url = classLoader.getResource("logo_mini.png")

    return if (url != null) {
        ImageIO.read(url)
    } else {
        BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB)
    }
}