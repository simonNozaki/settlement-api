package io.snozaki.service.payment.handler.merchant

import io.snozaki.service.payment.config.ApplicationRouter
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient

/**
 * MerchantHnadlerテストクラス
 */
@SpringBootTest
class MerchantHandlerTest {

    private lateinit var merchantHandler: MerchantHandler
    private lateinit var client: WebTestClient

    @Before
    fun setup() {
        merchantHandler = Mockito.mock(MerchantHandler::class.java)

        client = WebTestClient
                .bindToRouterFunction(ApplicationRouter(merchantHandler).router())
                .build()
    }

    /**
     * 正常系
     * リクエスト成功
     */
    @Test
    fun test001_001() {
        client.get().uri("/merchant/{id}", "mt00000001")
                .exchange()
                .expectBody()
                .jsonPath("$.id").isNotEmpty
                .jsonPath("$.id").isEqualTo("mt00000001")
                .jsonPath("$.name").isEqualTo("merchant1")
    }

    /**
     * 異常系
     * IDが空文字
     */
    @Test
    fun test002_001() {
        client.get().uri("/merchant/{id}", "")
                .exchange()
                .expectBody()
                .jsonPath("$.ok").isEqualTo(false)
                .jsonPath("$.message").isEqualTo("invalid merchant id.")
                .jsonPath("$.errors.id").isEqualTo("merchant1")
    }

}