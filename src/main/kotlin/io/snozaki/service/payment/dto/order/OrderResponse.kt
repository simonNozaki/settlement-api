package io.snozaki.service.payment.dto.order

import io.snozaki.service.payment.dto.AbstractResponse

/**
 * 注文レスポンスデータ
 */
class OrderResponse<OrderResponseElement> (
        override var ids: MutableList<OrderResponseElement>
    ) : AbstractResponse<OrderResponseElement> ()