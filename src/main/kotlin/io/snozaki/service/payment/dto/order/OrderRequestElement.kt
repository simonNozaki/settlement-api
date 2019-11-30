package io.snozaki.service.payment.dto.order

import io.snozaki.service.payment.dto.AbstractRequestElement

class OrderRequestElement(
        override var orderId: String
    ) : AbstractRequestElement ()