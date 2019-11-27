package io.snozaki.service.payment.dto.order

data class OrderRequest(
        var merchantId: String,
        var orderIds: MutableList<OrderRequestElement>
)