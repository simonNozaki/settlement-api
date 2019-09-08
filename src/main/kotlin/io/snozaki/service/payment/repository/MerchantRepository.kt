package io.snozaki.service.payment.repository

import io.snozaki.service.payment.entity.merchant.Merchant

class MerchantRepository {

    var merchants: MutableList<Merchant> = mutableListOf(
            Merchant("mt00000001", "merchant1", "事業者1", ""),
            Merchant("mt00000002", "merchant2", "事業者2", ""),
            Merchant("mt00000003", "merchant3", "事業者3", "")
    )
}