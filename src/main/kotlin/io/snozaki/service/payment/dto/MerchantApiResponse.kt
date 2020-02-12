package io.snozaki.service.payment.dto

import io.snozaki.service.payment.dto.error.CommonError
import io.snozaki.service.payment.entity.merchant.Merchant
import kotlinx.coroutines.channels.consumesAll

/**
 * 加盟店APIレスポンスクラス。
 */
class MerchantApiResponse : AbstractApiResponse<List<Merchant>>() {

    override var ok: Boolean
        get() = ok
        set(ok) { this.ok = ok }

    override var message: String?
        get() = message
        set(message) { this.message = message }

    override var errors: CommonError?
        get() = errors
        set(errors) { this.errors = errors }

    override var value: List<Merchant>
        get() = value
        set(value) { this.value = value }
}