package io.snozaki.service.payment.config

import io.snozaki.service.payment.consts.app.FUNCTION_BILLING
import io.snozaki.service.payment.consts.app.FUNCTION_DOMAIN_ORDER
import io.snozaki.service.payment.handler.BillingHandler
import kotlinx.coroutines.FlowPreview
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.*

@Configuration
class RouterConfig {

    @FlowPreview
    @Bean
    fun routeBilling(billingHandler: BillingHandler) = router {
        POST(FUNCTION_DOMAIN_ORDER + FUNCTION_BILLING)
    }
}