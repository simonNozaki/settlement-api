package io.snozaki.service.payment.entity.billing

data class Billing (
        var orderId: String,
        var billingStatus: String,
        var remindStatus: String,
        var payment: String
)