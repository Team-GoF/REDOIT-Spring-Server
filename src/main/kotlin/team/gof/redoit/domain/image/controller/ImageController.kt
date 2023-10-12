package team.gof.redoit.domain.image.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import team.gof.redoit.domain.image.service.ImageConvertingService

@RestController
@RequestMapping("/image")
class ImageController(
    private val imageConvertingService: ImageConvertingService
) {
    @PostMapping
    fun editImage(@RequestPart image: MultipartFile, @RequestParam prompt: String): ResponseEntity<String> {
        val result = imageConvertingService.execute(image, prompt)
        return ResponseEntity.ok(result)
    }


}