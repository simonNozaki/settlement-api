package io.snozaki.service.payment.controller

import io.snozaki.service.payment.consts.app.STATUS_INVALID_PARAMETERS
import io.snozaki.service.payment.dto.AbstractApiResponse
import io.snozaki.service.payment.dto.AbstractResponse
import io.snozaki.service.payment.dto.GeneralResponse
import io.snozaki.service.payment.dto.error.CommonError
import io.snozaki.service.payment.dto.error.ErrorApiResponse
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyAndAwait
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import reactor.core.publisher.Flux



fun <T : AbstractResponse<V>, V> respondFalse(): Flux<GeneralResponse<T>> {
    return Flux.just(GeneralResponse<T>(
            ok = false,
            message = STATUS_INVALID_PARAMETERS,
            value = null
    ))
}

/**
 * Bad Requestで処理します。
 */
suspend fun badRequestHandler(commonError: CommonError): ServerResponse =
        ServerResponse
                .badRequest()
                .bodyValueAndAwait(ErrorApiResponse().apply { ok = false; message = null; errors = commonError })


fun <T: AbstractApiResponse<R>, R> initHandler(): CommonResponseHandler<T, R> {
    return CommonResponseHandler()
}

class CommonResponseHandler <T: AbstractApiResponse<R>, R> (private var t: T) {

    /**
     * 条件を評価し、falseの場合第２引数のエラーに設定します。
     */
    fun test(f: () -> Boolean, errorKv: Pair<String, Any>): CommonResponseHandler<T, R> {
        if (!f.invoke()) {
            t.errors?.errors?.put(errorKv.first, errorKv.second)
        }

        return CommonResponseHandler(t)
    }

    /**
     * リポジトリクラスを実行します。
     */
    @Suppress("UNCHECKED_CAST")
    fun <V> accessRepository(v: V): CommonResponseHandler<T, R> {
        t.value = v as R
        return CommonResponseHandler(t)
    }

    /**
     * 結果を構築します。
     */
    suspend fun apply(): ServerResponse? {
        // エラーが一つ以上含まれる場合、Bad Requestで処理
        if(t.errors?.errors?.size!! > 0) {
            return ServerResponse
                    .badRequest()
                    .bodyValueAndAwait(ErrorApiResponse().apply { ok = false; message = null; errors = t.errors })
        }

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValueAndAwait(t)

    }

}

