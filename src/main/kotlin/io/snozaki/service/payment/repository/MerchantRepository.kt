package io.snozaki.service.payment.repository

import io.snozaki.service.payment.entity.merchant.Merchant

class MerchantRepository {

    var merchants: MutableList<Merchant> = mutableListOf(
            Merchant("", "", "", ""),
            Merchant("", "", "", ""),
            Merchant("", "", "", "")
    )
}