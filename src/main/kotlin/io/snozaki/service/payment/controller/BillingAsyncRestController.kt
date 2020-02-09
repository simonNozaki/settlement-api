package io.snozaki.service.payment.controller

import io.snozaki.service.payment.consts.app.API_VERSION
import io.snozaki.service.payment.dto.BillingApiResponse
import io.snozaki.service.payment.dto.billing.BillingRequest
import io.snozaki.service.payment.dto.billing.BillingRequestElement
import io.snozaki.service.payment.service.billing.BillingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 請求非同期処理REST Controller
 */
@RestController
@RequestMapping(API_VERSION)
class BillingAsyncRestController @Autowired constructor(private var billingService: BillingService) {

    fun bill(@RequestBody req: BillingRequest<BillingRequestElement>): BillingApiResponse {
        // 注文IDのリストに平坦化
        var orderIds: List<String> = req.objects.flatMap { elm: BillingRequestElement -> listOf(elm.orderId) }

        return BillingApiResponse().apply {
            ok = true
            message = null
            errors = null
            value = billingService.billOrders(orderIds, req.merchantId)
        }
    }
}