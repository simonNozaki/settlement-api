package io.snozaki.service.payment.config

import io.snozaki.service.payment.handler.merchant.MerchantHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.*

/**
 * Webアプリケーションルーティングクラス。
 */
@Configuration
class ApplicationRouter(private val merchantHandler: MerchantHandler) {

    @Bean
    fun router() = coRouter {
        accept(MediaType.APPLICATION_JSON).nest {
            GET("/merchant/{id}") {
                serverRequest: ServerRequest -> merchantHandler.getMerchantById(serverRequest)
            }
        }
    }
}