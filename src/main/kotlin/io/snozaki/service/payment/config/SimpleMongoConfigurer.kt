package io.snozaki.service.payment.config

import com.mongodb.MongoClient
import io.snozaki.service.payment.consts.app.MongoPropertyHolder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate

/**
 * MongoDBクライアント設定クラス
 */
@Configuration
class SimpleMongoConfigurer(private var mongoPropertyHolder: MongoPropertyHolder) {

    @Bean
    @Throws(Exception::class)
    fun mongo(): MongoClient {
        return MongoClient(mongoPropertyHolder.host)
    }

    @Bean
    @Throws(Exception::class)
    fun mongoTemplate(): MongoTemplate {
        return MongoTemplate(mongo(), mongoPropertyHolder.db)
    }
}