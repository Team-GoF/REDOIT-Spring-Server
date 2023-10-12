package team.gof.redoit.global.flask

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("flask")
class FlaskConfig(
    url: String
) {
    val url: String

    init {
        this.url = url
    }
}