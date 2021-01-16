import androidx.compose.desktop.Window
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import jdk.jshell.execution.Util
import java.awt.image.BufferedImage
import java.io.File
import java.io.FileNotFoundException
import javax.imageio.ImageIO
import kotlin.random.Random

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
            val firstImagePath = getRandomImagePath(null)
            val secondImagePath = getRandomImagePath(firstImagePath)
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    bitmap = imageResource(firstImagePath),
                    modifier = Modifier.preferredHeight(500.dp).preferredWidth(500.dp),
                    alignment = Alignment.Center
                )
                Image(
                    bitmap = imageResource(secondImagePath),
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

@Composable
fun getRandomImagePath(otherImagePath: String?): String {
    val classLoader = Util::class.java.classLoader
    val url = classLoader.getResource("pictureDeck") ?: throw FileNotFoundException("Cannot find the pictureDeck")

    val file = File(url.file)
    val imagePaths = file.listFiles() ?: throw FileNotFoundException("No pictures in the pictureDeck")
    var imagePath: String

    do {
        val index = Random.nextInt(imagePaths.size)
        imagePath = "pictureDeck/" + imagePaths[index].name
    } while (imagePath == otherImagePath)

    return imagePath
}
