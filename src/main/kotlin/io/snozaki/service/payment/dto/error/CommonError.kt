package io.snozaki.service.payment.dto.error

/**
 * 共通エラークラス。
 */
data class CommonError (

        /**
         * エラーマップ
         */
        var errors: MutableMap<String?, Any?>
)