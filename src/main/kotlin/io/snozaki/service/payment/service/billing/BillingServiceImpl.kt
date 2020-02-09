package io.snozaki.service.payment.service.billing

import io.snozaki.service.payment.dto.billing.BillingRequest
import io.snozaki.service.payment.dto.billing.BillingResponse
import io.snozaki.service.payment.entity.billing.Billing
import io.snozaki.service.payment.entity.order.Order
import io.snozaki.service.payment.repository.BillingRepository
import io.snozaki.service.payment.repository.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

/**
 * 請求サービス実装クラス
 */
@Service
class BillingServiceImpl(@Autowired private val billingRepository: BillingRepository, @Autowired private val orderRepository: OrderRepository) : BillingService {

    override fun bill(orderIds: List<String>, merchantId: String): Flux<Billing> {

        // 加盟店の注文一覧
        var targetOrders: MutableList<Order> = orderRepository.getOrderByOrderAndMerchant(orderIds, merchantId)
        var targetOrderIds: MutableList<String> = targetOrders.flatMap { listOf(it.id) }.toMutableList()

        // 対象の注文を請求中に更新
        return Flux.fromIterable(billingRepository.updateBillingStatusToFirst(targetOrderIds)).log()
    }

    override fun billOrders(orderIds: List<String>, merchantId: String): List<Billing> {
        var targetOrders: MutableList<Order> = orderRepository.getOrderByOrderAndMerchant(orderIds, merchantId)
        var targetOrderIds: MutableList<String> = targetOrders.flatMap { listOf(it.id) }.toMutableList()

        return billingRepository.updateBillingStatusToFirst(targetOrderIds)
    }
}