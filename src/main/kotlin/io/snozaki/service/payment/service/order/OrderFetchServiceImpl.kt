package io.snozaki.service.payment.service.order

import io.snozaki.service.payment.entity.order.Order
import io.snozaki.service.payment.repository.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

/**
 * 注文情報取得サービスの実装
 */
@Service
class OrderFetchServiceImpl(@Autowired private var orderRepository: OrderRepository) : OrderFetchService {

    /**
     * 加盟店の当該取引を取得する
     * @param orderIds 注文IDリスト
     * @param merchantId 加盟店ID
     * @return 注文情報可変リスト
     */
    override fun fetch(orderIds: List<String>, merchantId: String): Flux<Order> {
//        return Flux.fromIterable(orderRepository.getOrderByOrderAndMerchant(orderIds, merchantId)).log()
        return Flux.fromIterable(orderRepository.fetchOrderByOrderAndMerchant(orderIds, merchantId)).log()
    }
}