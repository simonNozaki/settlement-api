package io.snozaki.service.payment.service.order

import io.snozaki.service.payment.entity.order.Order
import io.snozaki.service.payment.repository.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import reactor.core.publisher.Flux

/**
 * 注文情報取得サービスの実装
 */
class OrderFetchServiceImpl(@Autowired private var orderRepository: OrderRepository) : OrderFetchService {

    /**
     * 加盟店の当該取引を取得する
     * @param orderIds 注文IDリスト
     * @param merchantId 加盟店ID
     * @return 注文情報可変リスト
     */
    override fun fetch(orderIds: List<String>, merchantId: String): Flux<Order> {

        var targetOrders: MutableList<Order> = mutableListOf()

        for(orderId in orderIds) {
            var merchantOrders: MutableList<Order> = orderRepository.getOrdersByMerchant(merchantId)
                    .filter { order: Order -> order.id == orderId }
                    .map { it }
                    .toMutableList()
            targetOrders.addAll(merchantOrders)
        }

        return Flux.fromIterable(targetOrders)
    }
}