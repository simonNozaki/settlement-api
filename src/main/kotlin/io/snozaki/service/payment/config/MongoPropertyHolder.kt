package io.snozaki.service.payment.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

@Component
@Configuration
@ConfigurationProperties(prefix="spring.data.mongodb")
class MongoPropertyHolder {

    lateinit var database: String

    lateinit var host: String

    lateinit var port: String

    lateinit var username: String

    lateinit var password: String

    lateinit var applicationname: String
}