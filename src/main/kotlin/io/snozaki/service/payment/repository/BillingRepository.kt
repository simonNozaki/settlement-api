package io.snozaki.service.payment.repository

import io.snozaki.service.payment.entity.billing.Billing
import org.springframework.stereotype.Repository

@Repository
class BillingRepository {

    var billings: MutableList<Billing> = mutableListOf(
            Billing("", "", "", ""),
            Billing("", "", "", ""),
            Billing("", "", "", "")
    )
}