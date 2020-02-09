package io.snozaki.service.payment.dto

import io.snozaki.service.payment.dto.error.CommonError
import io.snozaki.service.payment.entity.billing.Billing

/**
 * 請求APIレスポンスクラス。
 */
class BillingApiResponse : AbstractApiResponse<List<Billing>> () {
    override var ok: Boolean
        get() = ok
        set(ok: Boolean) { this.ok = ok }

    override var message: String?
        get() = this.message
        set(message: String?) { this.message = message }

    override var errors: CommonError?
        get() = this.errors
        set(errors: CommonError?) { this.errors = errors }

    override var value: List<Billing>
        get() = this.value
        set(value: List<Billing>) { this.value = value }
}