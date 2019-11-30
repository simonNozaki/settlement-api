package io.snozaki.service.payment.consts.app

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

@Component
@Configuration
@ConfigurationProperties(prefix="mongodb")
class MongoPropertyHolder {

    lateinit var db: String

    lateinit var host: String

    lateinit var port: String
}