package io.snozaki.service.payment.controller

import io.snozaki.service.payment.consts.app.STATUS_INVALID_PARAMETERS
import io.snozaki.service.payment.dto.AbstractResponse
import io.snozaki.service.payment.dto.GeneralResponse
import reactor.core.publisher.Flux

fun <T : AbstractResponse<V>, V> respondFalse(): Flux<GeneralResponse<T>> {
    return Flux.just(GeneralResponse<T>(
            ok = false,
            message = STATUS_INVALID_PARAMETERS,
            value = null
    ))
}
