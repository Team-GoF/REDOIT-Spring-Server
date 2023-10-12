package team.gof.redoit.domain.image.service

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import team.gof.redoit.global.flask.FlaskRestTemplate


@Service
class ImageConvertingService(
    private val flaskRestTemplate: FlaskRestTemplate,
) {
    fun execute(image: MultipartFile, prompt: String): String {
        return flaskRestTemplate.sendToFlask(image, prompt)
    }
}