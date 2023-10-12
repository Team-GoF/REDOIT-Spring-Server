package team.gof.redoit.global.png

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO


@Component
class GenerateMaskPng {
    private val log = LoggerFactory.getLogger(GenerateMaskPng::class.java)

    fun execute(file: MultipartFile): ByteArray {
        val image: BufferedImage = ImageIO.read(file.inputStream)
        val argbImage = BufferedImage(image.width, image.height, BufferedImage.TYPE_INT_ARGB)
        val g = argbImage.createGraphics()
        g.drawImage(image, 0, 0, null)
        g.dispose()

        val width = argbImage.width
        val height = argbImage.height

        log.info("$width")
        log.info("$height")

        val startX = width / 10
        val startY = height / 10
        val endX = startX * 9
        val endY = startY * 9

        for(x in startX until  endX) {
            for(y in startY until endY) {
                val rgb = argbImage.getRGB(x, y)

                val newRGB = (0 shl 24) or (rgb and 0x00FFFFFF)

                argbImage.setRGB(x, y, newRGB)
            }
        }

        val byteArrayOutputStream = ByteArrayOutputStream()
        ImageIO.write(argbImage, "png", byteArrayOutputStream)

        return byteArrayOutputStream.toByteArray()
    }
}