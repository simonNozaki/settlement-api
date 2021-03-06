package io.snozaki.service.payment.controller.order

import io.snozaki.service.payment.config.AppLogger
import io.snozaki.service.payment.consts.app.API_VERSION
import io.snozaki.service.payment.consts.app.FUNCTION_DOMAIN_ORDER
import io.snozaki.service.payment.consts.app.STATUS_MESSAGE_OK
import io.snozaki.service.payment.controller.respondFalse
import io.snozaki.service.payment.dto.GeneralResponse
import io.snozaki.service.payment.dto.order.OrderRequest
import io.snozaki.service.payment.dto.order.OrderRequestElement
import io.snozaki.service.payment.dto.order.OrderResponse
import io.snozaki.service.payment.dto.order.OrderResponseElement
import io.snozaki.service.payment.entity.order.Order
import io.snozaki.service.payment.service.order.OrderFetchService
import io.snozaki.service.payment.util.isValidRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.lang.Exception

/**
 * 注文REST Controllerクラス
 */
@RestController
@RequestMapping(API_VERSION)
class OrderRestController @Autowired constructor(private var orderFetchService: OrderFetchService) {

    @RequestMapping(value=[FUNCTION_DOMAIN_ORDER], consumes=[MediaType.APPLICATION_JSON_VALUE], method=[RequestMethod.POST])
    @Throws(Exception::class)
    fun execute(@RequestBody req: OrderRequest<OrderRequestElement>): Flux<GeneralResponse<OrderResponse<OrderResponseElement>>> {

        // 引数チェック、不正な場合空のFluxを返却
        if(!isValidRequest(req)) respondFalse<OrderResponse<OrderResponseElement>, OrderResponseElement>()

        // 注文IDの平坦化
        var orderIds: List<String> = req.objects.flatMap { elm: OrderRequestElement -> listOf(elm.orderId) }

        // 正常系処理
        return orderFetchService.fetch(orderIds, req.merchantId)
                .doFirst { AppLogger.trace("Controllerの処理を開始します。") }
                .map { t: Order -> GeneralResponse(
                        ok = true,
                        message = STATUS_MESSAGE_OK,
                        value = OrderResponse(mutableListOf(OrderResponseElement(t)))
                ) }
                .doOnComplete { AppLogger.trace("Controllerの処理を正常に終了しました。") }
                .doOnError { e: Throwable -> AppLogger.error(e) }
                .log()
    }
}