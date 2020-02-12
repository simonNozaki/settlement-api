package io.snozaki.service.payment.repository

import io.snozaki.service.payment.entity.merchant.Merchant

class MerchantRepository {

    private var merchants: MutableList<Merchant> = mutableListOf(
            Merchant("mt00000001", "merchant1", "事業者1", ""),
            Merchant("mt00000002", "merchant2", "事業者2", ""),
            Merchant("mt00000003", "merchant3", "事業者3", "")
    )

    /**
     * 加盟店IDをもとに加盟店情報を取得します。
     */
    fun getMerchantById(merchantId: String): Merchant {
        if(this.merchants.filter { it.id == merchantId }.isNullOrEmpty()) {
            return Merchant("", "", "", "")
        }

        return this.merchants.filter { it.id == merchantId }[0]
    }
}