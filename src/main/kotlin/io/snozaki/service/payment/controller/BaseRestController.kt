package io.snozaki.service.payment.controller

import io.snozaki.service.payment.consts.app.STATUS_INVALID_PARAMETERS
import io.snozaki.service.payment.dto.GeneralResponse
import reactor.core.publisher.Flux

fun <T> respondFalse(): Flux<out GeneralResponse<T>> {
    return Flux.just(GeneralResponse(
            ok = false,
            message = STATUS_INVALID_PARAMETERS,
            value = null
    ))
}
