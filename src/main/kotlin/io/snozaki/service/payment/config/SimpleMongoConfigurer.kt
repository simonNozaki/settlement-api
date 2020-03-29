package io.snozaki.service.payment.config

import com.mongodb.MongoClient
import com.mongodb.MongoClientOptions
import com.mongodb.MongoCredential
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoConfiguration
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoDbFactory
import org.springframework.data.mongodb.MongoDbFactory


/**
 * MongoDBクライアント設定クラス
 */
@Configuration
class SimpleMongoConfigurer(private val mongoPropertyHolder: MongoPropertyHolder) : AbstractMongoConfiguration() {
    override fun getDatabaseName(): String {
        return mongoPropertyHolder.database
    }

    override fun mongoClient(): MongoClient {
        var credential = MongoCredential.createCredential(mongoPropertyHolder.username, databaseName, mongoPropertyHolder.password.toCharArray())
        var mongoClientOptions = MongoClientOptions
                .builder()
                .applicationName(mongoPropertyHolder.applicaitionname)
                .build()
        return MongoClient(mongoPropertyHolder.host, mongoClientOptions)
    }

    @Bean
    @Throws(Exception::class)
    override fun mongoDbFactory(): MongoDbFactory {
        return SimpleMongoDbFactory(mongoClient(), databaseName)
    }

    @Bean
    @Throws(Exception::class)
    override fun mongoTemplate(): MongoTemplate {
        return MongoTemplate(mongoClient(), databaseName)
    }
}