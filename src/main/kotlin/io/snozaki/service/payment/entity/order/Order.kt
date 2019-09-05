package io.snozaki.service.payment.entity.order

data class Order (
        var id: String,
        var merchantId: String,
        var merchantOrderId: String,
        var billingAmount: String,
        var customer: Customer,
        var items: MutableList<Item>
)