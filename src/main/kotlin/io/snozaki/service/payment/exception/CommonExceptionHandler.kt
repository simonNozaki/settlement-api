package io.snozaki.service.payment.exception

import io.snozaki.service.payment.config.AppLogger
import io.snozaki.service.payment.consts.app.STATUS_MESSAGE_INTERNAL_SERVER_ERROR
import io.snozaki.service.payment.dto.GeneralResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

/**
 * 共通例外ハンドラクラス
 */
// @RestControllerAdvice
class CommonExceptionHandler : ResponseEntityExceptionHandler() {

    override fun handleExceptionInternal(ex: Exception, body: Any?, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        AppLogger.error(ex)
        return super.handleExceptionInternal(
                ex,
                GeneralResponse(
                        ok = false,
                        message = STATUS_MESSAGE_INTERNAL_SERVER_ERROR,
                        value = request
                ),
                HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                request
        )
    }
}