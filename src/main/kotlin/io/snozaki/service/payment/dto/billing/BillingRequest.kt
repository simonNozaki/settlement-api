package io.snozaki.service.payment.dto.billing

data class BillingRequest (
        var merchantId: String,
        var billings: MutableList<BillingRequestElement>
)