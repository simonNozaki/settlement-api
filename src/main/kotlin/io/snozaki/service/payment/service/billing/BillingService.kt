package io.snozaki.service.payment.service.billing

import io.snozaki.service.payment.dto.billing.BillingRequest
import io.snozaki.service.payment.entity.billing.Billing
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
interface BillingService {

    /**
     * 該当の注文を請求状態にする
     * @param orderIds 注文IDリスト
     * @param merchantId 加盟店ID
     * @return 請求のFlux
     */
    fun bill(orderIds: List<String>, merchantId: String): Flux<Billing>

    fun billOrders(orderIds: List<String>, merchantId: String): List<Billing>
}