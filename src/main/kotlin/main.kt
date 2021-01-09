import androidx.compose.desktop.Window
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import jdk.jshell.execution.Util
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

fun main() {
    Window(
        title = "Would you rather?",
        size = IntSize(1000, 500),
        icon = getWindowIcon(),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    bitmap = imageResource("logo.png"),
                    modifier = Modifier.preferredHeight(500.dp).preferredWidth(500.dp),
                    alignment = Alignment.Center
                )
                Image(
                    bitmap = imageResource("logo_mini.png"),
                    modifier = Modifier.preferredHeight(500.dp).preferredWidth(500.dp),
                    alignment = Alignment.Center
                )
            }
        }
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