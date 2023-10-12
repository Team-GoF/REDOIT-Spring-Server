package team.gof.redoit.global.png

import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO

@Component
class ConvertToPng {
    fun execute(file: MultipartFile): ByteArray {
        val image: BufferedImage = ImageIO.read(file.inputStream)

        val byteArrayOutputStream = ByteArrayOutputStream()
        ImageIO.write(image, "png", byteArrayOutputStream)

        return byteArrayOutputStream.toByteArray()
    }
}