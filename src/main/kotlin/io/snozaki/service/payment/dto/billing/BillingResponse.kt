package io.snozaki.service.payment.dto.billing

import io.snozaki.service.payment.dto.AbstractResponse

/**
 * 請求レスポンスデータ
 */
data class BillingResponse<BillingResponseElement> (
        override var ids: MutableList<BillingResponseElement>
    ) : AbstractResponse<BillingResponseElement>(

)