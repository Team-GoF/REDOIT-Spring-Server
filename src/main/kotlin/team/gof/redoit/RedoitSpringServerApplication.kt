package team.gof.redoit

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class RedoitSpringServerApplication

fun main(args: Array<String>) {
    runApplication<RedoitSpringServerApplication>(*args)
}
