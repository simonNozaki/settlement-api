package io.snozaki.service.payment.config

import com.mongodb.MongoClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate

/**
 * MongoDBクライアント設定クラス
 */
@Configuration
class SimpleMongoConfigurer {

    @Bean
    @Throws(Exception::class)
    fun mongo(): MongoClient {
        return MongoClient("localhost")
    }

    @Bean
    @Throws(Exception::class)
    fun mongoTemplate(): MongoTemplate {
        return MongoTemplate(mongo(), "settlement")
    }
}