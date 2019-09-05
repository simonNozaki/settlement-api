package io.snozaki.service.payment.service.billing

import io.snozaki.service.payment.dto.billing.BillingRequest
import io.snozaki.service.payment.dto.billing.BillingResponse
import io.snozaki.service.payment.entity.billing.Billing
import io.snozaki.service.payment.repository.BillingRepository
import io.snozaki.service.payment.repository.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import reactor.core.publisher.Flux

class BillingServiceImpl(@Autowired billingRepository: BillingRepository, @Autowired orderRepository: OrderRepository) : BillingService {

    /**
     * リポジトリの結果をzipしてmonoインスタンスを返却する
     */
    override fun bill(req: BillingRequest): Flux<Billing> {
        TODO()
    }
}