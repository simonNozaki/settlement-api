package io.snozaki.service.payment.service.order

import io.snozaki.service.payment.entity.order.Order
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

/**
 * 注文情報取得サービス
 */
@Service
interface OrderFetchService {

    fun fetch(orderIds: List<String>, merchantId: String): Flux<Order>
}