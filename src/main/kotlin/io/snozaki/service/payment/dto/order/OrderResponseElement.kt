package io.snozaki.service.payment.dto.order

import io.snozaki.service.payment.entity.order.Order

data class OrderResponseElement(
    var order: Order
)