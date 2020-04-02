package io.snozaki.service.payment.handler.merchant

import io.snozaki.service.payment.config.AppLogger
import io.snozaki.service.payment.controller.CommonErrorHandler
import io.snozaki.service.payment.dto.error.CommonError
import io.snozaki.service.payment.repository.MerchantRepository
import io.snozaki.service.payment.util.isValidMerchantId

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyValueAndAwait

/**
 * 加盟店オブジェクトハンドラクラス。
 */
@Component
class MerchantHandler(private val merchantRepository: MerchantRepository) {

    /**
     * 加盟店IDで加盟店情報を取得します。
     */
    suspend fun getMerchantById(request: ServerRequest): ServerResponse {

        AppLogger.trace("Controllerの処理を開始します。")

        // 加盟店IDが不正なら400を返却
        if (!request.pathVariable("id").isValidMerchantId()) {
            return CommonErrorHandler.badRequest(CommonError((mutableMapOf(Pair(request.pathVariable("id"), "invalid merchant id.")))))
        }

        return try {
            ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValueAndAwait(merchantRepository.getMerchantById(request.pathVariable("id")))
                .apply { AppLogger.trace("Controllerの処理を開始します。") }
        } catch (e: Exception) {
            CommonErrorHandler.internalServerError(CommonError((mutableMapOf(Pair(request.pathVariable("id"), "System Error occurred.")))))
                .apply { AppLogger.trace("Controllerの処理を終了します。") }
        }
    }
}