package io.snozaki.service.payment.dto

import io.snozaki.service.payment.dto.error.CommonError

/**
 * APIレスポンス抽象クラス。
 */
abstract class AbstractApiResponse<T> {
    abstract var ok: Boolean
    abstract var message: String?
    abstract var errors: CommonError?
    abstract var value: T
}