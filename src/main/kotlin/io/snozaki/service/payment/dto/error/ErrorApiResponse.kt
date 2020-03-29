package io.snozaki.service.payment.dto.error

import io.snozaki.service.payment.dto.AbstractApiResponse

/**
 * APIエラーレスポンスクラス。
 */
class ErrorApiResponse : AbstractApiResponse<Any>() {

    override var ok: Boolean = false

    override var message: String? = null

    override var errors: CommonError? = null

    override lateinit var value: Any
}