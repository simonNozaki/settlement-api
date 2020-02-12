package io.snozaki.service.payment.config

import io.snozaki.service.payment.handler.merchant.MerchantHandler
import kotlinx.coroutines.runBlocking
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.*

/**
 * Webアプリケーションルーティングクラス。
 */
@Configuration
class ApplicationRouter {

    @Bean
    fun applicationRouter(merchantHandler: MerchantHandler) = router {
        accept(MediaType.APPLICATION_JSON).nest {
            GET("/merchant/{id}") {
                serverRequest: ServerRequest -> runBlocking { merchantHandler.getMerchantById(serverRequest) }
            }
        }
    }
}