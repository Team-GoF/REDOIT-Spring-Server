package team.gof.redoit.global.flask

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("flask")
class FlaskConfig(
    url: String
) {
    val url: String

    init {
        this.url = url
    }
}