package io.snozaki.service.payment.controller.billing

import io.snozaki.service.payment.config.AppLogger
import io.snozaki.service.payment.consts.app.API_VERSION
import io.snozaki.service.payment.consts.app.FUNCTION_BILLING
import io.snozaki.service.payment.consts.app.FUNCTION_DOMAIN_ORDER
import io.snozaki.service.payment.consts.app.STATUS_MESSAGE_OK
import io.snozaki.service.payment.controller.respondFalse
import io.snozaki.service.payment.dto.GeneralResponse
import io.snozaki.service.payment.dto.billing.BillingRequest
import io.snozaki.service.payment.dto.billing.BillingRequestElement
import io.snozaki.service.payment.dto.billing.BillingResponse
import io.snozaki.service.payment.dto.billing.BillingResponseElement
import io.snozaki.service.payment.entity.billing.Billing
import io.snozaki.service.payment.entity.order.Order
import io.snozaki.service.payment.service.billing.BillingService
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
 * 請求REST Controllerクラス
 */
@RestController
@RequestMapping(API_VERSION)
class BillingRestController @Autowired constructor(private var billingService: BillingService, private var orderFetchService: OrderFetchService) {

    /**
     * 実装メソッド
     */
    @RequestMapping(value=[FUNCTION_DOMAIN_ORDER + FUNCTION_BILLING], consumes=[MediaType.APPLICATION_JSON_UTF8_VALUE], method=[RequestMethod.POST])
    @Throws(Exception::class)
    fun bill(@RequestBody req: BillingRequest<BillingRequestElement>): Flux<GeneralResponse<BillingResponse<BillingResponseElement>>> {

        // 引数チェック、不正な場合空のFluxを返却
        if(!isValidRequest(req)) respondFalse<BillingResponse<BillingResponseElement>, BillingResponseElement>()

        // 注文IDのリストに平坦化
        var orderIds: List<String> = req.objects.flatMap { elm: BillingRequestElement -> listOf(elm.orderId) }

        // 正常系処理
        return orderFetchService.fetch(orderIds, req.merchantId)
                .doFirst {
                    AppLogger.trace("Controllerの処理を開始します。")
                }
                .flatMap { orders: Order -> billingService.bill(listOf(orders.id), req.merchantId) }
                .map { t: Billing ->
                    GeneralResponse(
                            ok = true,
                            message = STATUS_MESSAGE_OK,
                            value = BillingResponse(mutableListOf(BillingResponseElement(t.orderId, t.billingStatus))))
                }
                .doOnComplete {
                    AppLogger.trace("Controllerの処理を正常に終了しました。")
                }
                .doOnError { e: Throwable -> AppLogger.error(e) }
                .log()
    }
}