package io.snozaki.service.payment.controller

import io.snozaki.service.payment.consts.app.STATUS_INVALID_PARAMETERS
import io.snozaki.service.payment.dto.AbstractResponse
import io.snozaki.service.payment.dto.GeneralResponse
import io.snozaki.service.payment.dto.error.CommonError
import io.snozaki.service.payment.dto.error.ErrorApiResponse
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import reactor.core.publisher.Flux
import java.lang.RuntimeException


fun <T : AbstractResponse<V>, V> respondFalse(): Flux<GeneralResponse<T>> {
    return Flux.just(GeneralResponse<T>(
            ok = false,
            message = STATUS_INVALID_PARAMETERS,
            value = null
    ))
}

/**
 * 非同期処理ハンドラー用エラーレスポンスハンドラ
 */
class CommonErrorHandler {
    companion object {
        /**
         * Bad Requestで処理します。
         * @param commonError 共通エラー。
         * @return Bad Request相当のエラーオブジェクト
         */
        suspend fun badRequest(commonError: CommonError): ServerResponse = generateResponse(commonError, 400)

        /**
         * Internal Server Errorで処理します。
         * @param commonError 共通エラー。
         * @return Internal Server Error相当のエラーオブジェクト
         */
        suspend fun internalServerError(commonError: CommonError): ServerResponse = generateResponse(commonError, 500)

        private suspend inline fun generateResponse(commonError: CommonError, status: Int): ServerResponse {

            return when(status) {
                400 ->
                    ServerResponse
                            .badRequest()
                            .bodyValueAndAwait(ErrorApiResponse().apply { ok = false; message = null; errors = commonError; value = "" })
                500 ->
                    ServerResponse
                            .status(500)
                            .bodyValueAndAwait(ErrorApiResponse().apply { ok = false; message = null; errors = commonError; value = "" })
                else ->
                    throw RuntimeException("適切なステータスコードが設定されていません.")
            }
        }
    }
}


