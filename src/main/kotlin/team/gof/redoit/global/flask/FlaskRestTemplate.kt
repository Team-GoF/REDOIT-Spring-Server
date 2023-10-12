package team.gof.redoit.global.flask

import org.slf4j.LoggerFactory
import org.springframework.core.io.ByteArrayResource
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate
import org.springframework.web.multipart.MultipartFile
import team.gof.redoit.global.png.GenerateMaskPng


@Component
class FlaskRestTemplate(
    private val restTemplate: RestTemplate,
    private val generateMaskPng: GenerateMaskPng,
    private val flaskConfig: FlaskConfig
) {
    private val log = LoggerFactory.getLogger(FlaskRestTemplate::class.java)

    fun sendToFlask(image: MultipartFile, prompt: String): String {
        val httpHeaders = HttpHeaders()
        httpHeaders.contentType = MediaType(MediaType.MULTIPART_FORM_DATA)

        log.info("byte is ${image.bytes}")

        val body: MultiValueMap<String, Any> = LinkedMultiValueMap()
        addBody("image", image.bytes, image.originalFilename, body)

        val emptyImgByteArray = generateMaskPng.execute(file = image)

        addBody("mask", emptyImgByteArray, "mask.png", body)

        val requestCallback = restTemplate.httpEntityCallback<Any>(HttpEntity<MultiValueMap<String, Any>>(body, httpHeaders))
        val responseExtractor = restTemplate.responseEntityExtractor<String>(String::class.java)

        val responseEntity = restTemplate.execute("${flaskConfig.url}/upload?prompt=$prompt", HttpMethod.POST, requestCallback, responseExtractor)
            ?: throw RuntimeException()
        return responseEntity.body ?: throw RuntimeException()
    }

    private fun addBody(key: String, bytes: ByteArray, fileName: String?, body: MultiValueMap<String, Any>) {
        body.add(key, object : ByteArrayResource(bytes)
        {
            override fun getFilename(): String? {
                return fileName
            }
        })
    }
}