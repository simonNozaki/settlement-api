package io.snozaki.service.payment.dto.billing

data class BillingResponseElement (
        var orderId: String,
        var billingStatus: String
)