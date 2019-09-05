package io.snozaki.service.payment.service.billing

import io.snozaki.service.payment.dto.billing.BillingRequest
import io.snozaki.service.payment.entity.billing.Billing
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
interface BillingService {

    fun bill(req: BillingRequest): Flux<Billing>
}