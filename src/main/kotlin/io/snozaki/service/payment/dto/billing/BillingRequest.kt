package io.snozaki.service.payment.dto.billing

import io.snozaki.service.payment.dto.AbstractRequest

/**
 * 請求リクエストデータ
 */
class BillingRequest<BillingRequestElement> (
        override var merchantId: String,
        override var objects: MutableList<BillingRequestElement>
    ) : AbstractRequest<BillingRequestElement> ()