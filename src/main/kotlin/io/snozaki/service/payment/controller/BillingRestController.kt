package io.snozaki.service.payment.controller

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
import reactor.util.function.Tuple2

@RestController
@RequestMapping("/v1")
class BillingRestController(@Autowired private val billingService: BillingService, @Autowired private var orderFetchService: OrderFetchService) {

    @RequestMapping(value=["/order/billing"], consumes=[MediaType.APPLICATION_JSON_UTF8_VALUE], method=[RequestMethod.POST])
    fun bill(@RequestBody req: BillingRequest): GeneralResponse<BillingResponse> {

        // 注文IDのリストに抽出
        var orderIds: List<String> = req.billings.flatMap { elm: BillingRequestElement -> mutableListOf(elm.orderId) }

        Flux.zip(
                orderFetchService.fetch(orderIds, req.merchantId),
                billingService.bill(req)
        ).map { t: Tuple2<Order, Billing> -> {
            var billed: Billing = t.t2
            GeneralResponse(
                    ok = true,
                    message = "",
                    value = BillingResponse(mutableListOf(BillingResponseElement(billed.orderId, ""))))
            }
        }
        
        TODO()
    }
}