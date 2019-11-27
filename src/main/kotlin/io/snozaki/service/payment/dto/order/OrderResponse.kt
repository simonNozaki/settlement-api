package io.snozaki.service.payment.dto.order

data class OrderResponse (
        var orders: MutableList<OrderResponseElement>
)