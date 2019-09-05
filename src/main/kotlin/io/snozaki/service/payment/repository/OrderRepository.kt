package io.snozaki.service.payment.repository

import io.snozaki.service.payment.entity.merchant.Merchant
import io.snozaki.service.payment.entity.order.Customer
import io.snozaki.service.payment.entity.order.Item
import io.snozaki.service.payment.entity.order.Order
import org.springframework.stereotype.Repository

@Repository
class OrderRepository {

    var orders: MutableList<Order> = mutableListOf(
            Order("", "", "", "",
                Customer("", "", "", "", ""),
                mutableListOf(Item("", ""))
            ),
            Order("", "", "", "",
                    Customer("", "", "", "", ""),
                    mutableListOf(Item("", ""))
            ),
            Order("", "", "", "",
                    Customer("", "", "", "", ""),
                    mutableListOf(Item("", ""))
            ),
            Order("", "", "", "",
                    Customer("", "", "", "", ""),
                    mutableListOf(Item("", ""))
            )
    )

    /**
     * 加盟店IDで注文を取得する
     * @param merchantId 加盟店ID
     * @return 注文リスト
     */
    fun getOrdersByMerchant(merchantId: String): MutableList<Order> {
        return this.orders.filter { it.merchantId == merchantId }.map { it }.toMutableList()
    }

}