package io.snozaki.service.payment.dto.error

import io.snozaki.service.payment.dto.AbstractApiResponse

/**
 * APIエラーレスポンスクラス。
 */
class ErrorApiResponse : AbstractApiResponse<Nothing>() {

    override var ok: Boolean
        get() = this.ok
        set(ok) { this.ok = ok }

    override var message: String?
        get() = this.message
        set(message) { this.message = message }

    override var errors: CommonError?
        get() =  this.errors
        set(errors) { this.errors = errors }

    override var value: Nothing
        get() =  this.value
        set(value) { this.value = value }
}