package io.snozaki.service.payment.dto.billing

import io.snozaki.service.payment.dto.AbstractRequestElement

data class BillingRequestElement (
        override var orderId: String
)  : AbstractRequestElement()