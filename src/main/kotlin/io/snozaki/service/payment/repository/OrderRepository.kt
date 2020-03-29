package io.snozaki.service.payment.repository

import io.snozaki.service.payment.config.SimpleMongoConfigurer
import io.snozaki.service.payment.entity.order.Customer
import io.snozaki.service.payment.entity.order.Item
import io.snozaki.service.payment.entity.order.Order
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository

@Repository
class OrderRepository (private val simpleMongoConfigurer: SimpleMongoConfigurer) {

    var orders: MutableList<Order> = mutableListOf(
            Order("190908000001", "mt00000001", "merchant001", "1000",
                Customer("", "", "", "", ""),
                mutableListOf(Item("hat", "1000", "1"))
            ),
            Order("190908000002", "mt00000001", "merchant002", "3000",
                    Customer("", "", "", "", ""),
                    mutableListOf(Item("hat", "1000", "3"))
            ),
            Order("190908000003", "mt00000001", "merchant003", "5000",
                    Customer("", "", "", "", ""),
                    mutableListOf(Item("hat", "1000", "5"))
            ),
            Order("190908000004", "mt00000001", "merchant004", "7000",
                    Customer("", "", "", "", ""),
                    mutableListOf(Item("hat", "1000", "7"))
            )
    )

    /**
     * 加盟店IDで注文を取得する
     * @param merchantId 加盟店ID
     * @return 注文リスト
     */
    fun getOrdersByMerchant(merchantId: String): MutableList<Order> {
        return this.orders.filter { it.merchantId == merchantId }.toMutableList()
    }

    /**
     * 注文IDで注文情報を取得する
     */
    fun getOrdersByOrderId(orderIds: List<String>): MutableList<Order> {
        return this.orders.filter { order: Order -> orderIds.contains(order.id) }.toMutableList()
    }

    /**
     * 加盟店ID、注文IDで注文情報の一覧を取得する
     */
    fun getOrderByOrderAndMerchant(orderIds: List<String>, merchantId: String): MutableList<Order> {
        return this.orders
                .filter { order: Order -> order.merchantId.equals(merchantId) }
                .filter { order: Order -> orderIds.contains(order.id) }
                .toMutableList()
    }

    fun fetchOrderByOrderAndMerchant(orderIds: List<String>, merchantId: String): MutableList<Order> {
        var criteria: Criteria = Criteria.where("merchantId").`is`(merchantId).and("orderId").`in`(orderIds)
        var query: Query = Query(criteria)
        return simpleMongoConfigurer.mongoTemplate().find(query, Order::class.java)
    }

}