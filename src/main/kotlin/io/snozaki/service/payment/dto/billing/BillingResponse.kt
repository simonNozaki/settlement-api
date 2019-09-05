package io.snozaki.service.payment.dto.billing

data class BillingResponse (
        var billings: MutableList<BillingResponseElement>
)