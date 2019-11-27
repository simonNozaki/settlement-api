package io.snozaki.service.payment.controller

import io.snozaki.service.payment.config.trace
import io.snozaki.service.payment.consts.app.STATUS_MESSAGE_OK
import io.snozaki.service.payment.dto.GeneralResponse
import io.snozaki.service.payment.dto.order.OrderRequest
import io.snozaki.service.payment.dto.order.OrderRequestElement
import io.snozaki.service.payment.dto.order.OrderResponse
import io.snozaki.service.payment.dto.order.OrderResponseElement
import io.snozaki.service.payment.entity.order.Order
import io.snozaki.service.payment.service.order.OrderFetchService
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
@RequestMapping("/v1")
class OrderRestController @Autowired constructor(private var orderFetchService: OrderFetchService){

    @RequestMapping(value=["/order"], consumes=[MediaType.APPLICATION_JSON_UTF8_VALUE], method=[RequestMethod.POST])
    @Throws(Exception::class)
    fun fetchOrderByMerchantAndOrderIds(@RequestBody req: OrderRequest): Flux<GeneralResponse<OrderResponse>> {

        // 注文IDの平坦化
        var orderIds: List<String> = req.orderIds.flatMap { elm: OrderRequestElement -> listOf(elm.orderId) }

        return orderFetchService.fetch(orderIds, req.merchantId)
                .doFirst { trace("Controllerの処理を開始します。実行スレッド:${Thread.currentThread().name}") }
                .map { t: Order -> GeneralResponse(
                        ok = true,
                        message = STATUS_MESSAGE_OK,
                        value = OrderResponse(mutableListOf(OrderResponseElement(t)))
                ) }
                .doOnComplete { trace("Controllerの処理を正常に終了しました。実行スレッド:${Thread.currentThread().name}") }
                .doOnError { e: Throwable -> error(e) }
                .log()
    }
}