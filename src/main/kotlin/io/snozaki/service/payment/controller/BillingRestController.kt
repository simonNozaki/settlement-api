package io.snozaki.service.payment.controller

import io.snozaki.service.payment.config.trace
import io.snozaki.service.payment.config.error
import io.snozaki.service.payment.consts.app.STATUS_MESSAGE_OK
import io.snozaki.service.payment.dto.GeneralResponse
import io.snozaki.service.payment.dto.billing.BillingRequest
import io.snozaki.service.payment.dto.billing.BillingRequestElement
import io.snozaki.service.payment.dto.billing.BillingResponse
import io.snozaki.service.payment.dto.billing.BillingResponseElement
import io.snozaki.service.payment.entity.billing.Billing
import io.snozaki.service.payment.entity.order.Order
import io.snozaki.service.payment.service.billing.BillingService
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
 * 請求REST Controllerクラス
 */
@RestController
@RequestMapping("/v1")
class BillingRestController @Autowired constructor(private var billingService: BillingService, private var orderFetchService: OrderFetchService) {

    /**
     * 実装メソッド
     */
    @RequestMapping(value=["/order/billing"], consumes=[MediaType.APPLICATION_JSON_UTF8_VALUE], method=[RequestMethod.POST])
    @Throws(Exception::class)
    fun bill(@RequestBody req: BillingRequest): Flux<GeneralResponse<BillingResponse>> {

        // 注文IDのリストに平坦化
        var orderIds: List<String> = req.billings.flatMap { elm: BillingRequestElement -> listOf(elm.orderId) }

        return orderFetchService.fetch(orderIds, req.merchantId)
                .doFirst { trace("Controllerの処理を開始します。") }
                .flatMap { orders: Order -> billingService.bill(orderIds, req.merchantId) }
                .map { t: Billing ->
                    GeneralResponse(
                            ok = true,
                            message = STATUS_MESSAGE_OK,
                            value = BillingResponse(mutableListOf(BillingResponseElement(t.orderId, t.billingStatus))))
                }
                .doOnComplete { trace("Controllerの処理を正常に終了しました。") }
                .doOnError { e: Throwable -> error(e) }
                .log()
    }
}