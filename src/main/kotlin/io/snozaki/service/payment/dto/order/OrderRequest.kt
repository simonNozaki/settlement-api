package io.snozaki.service.payment.dto.order

import io.snozaki.service.payment.dto.AbstractRequest

/**
 * 注文リクエストデータ
 */
class OrderRequest<OrderRequestElement>(
        override var merchantId: String,
        override var objects: MutableList<OrderRequestElement>
    ) : AbstractRequest<OrderRequestElement>()