package io.snozaki.service.payment.handler.merchant

import io.snozaki.service.payment.controller.badRequestHandler
import io.snozaki.service.payment.controller.initHandler
import io.snozaki.service.payment.dto.MerchantApiResponse
import io.snozaki.service.payment.dto.error.CommonError
import io.snozaki.service.payment.entity.merchant.Merchant
import io.snozaki.service.payment.repository.MerchantRepository
import io.snozaki.service.payment.util.isValidMerchantId

import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono

/**
 * 加盟店オブジェクトハンドラクラス。
 */
@Configuration
class MerchantHandler(private val merchantRepository: MerchantRepository) {

    /**
     * 加盟店IDで加盟店情報を取得します。
     */
    suspend fun getMerchantById(request: ServerRequest): Mono<ServerResponse> {

        return initHandler<MerchantApiResponse, List<Merchant>>()
                .test({isValidMerchantId(request.pathVariable("id"))}, Pair("${request.pathVariable("id")}", "invalid merchant id."))
                .accessRepository(merchantRepository.getMerchantById(request.pathVariable("id")))
                .apply()
                ?: run {  }


        if (!isValidMerchantId(request.pathVariable("id"))) {
            return badRequestHandler(CommonError((mutableMapOf(Pair(request.pathVariable("id"), "invalid merchant id."))))).toMono()
        }

        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValueAndAwait(merchantRepository.getMerchantById(request.pathVariable("id")))
                .toMono()
    }
}