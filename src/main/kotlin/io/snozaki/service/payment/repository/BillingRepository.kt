package io.snozaki.service.payment.repository

import io.snozaki.service.payment.consts.model.*
import io.snozaki.service.payment.entity.billing.Billing
import org.springframework.stereotype.Repository

/**
 * 請求リポジトリクラス
 */
@Repository
class BillingRepository {

    var billings: MutableList<Billing> = mutableListOf(
            Billing("190908000001", "mt00000001", BILLING_STATUS_NOT_BILLING, REMIND_STATUS_0, PAYMENT_NOT_YET),
            Billing("190908000002", "mt00000001", BILLING_STATUS_NOT_BILLING, REMIND_STATUS_0, PAYMENT_NOT_YET),
            Billing("190908000003", "mt00000001", BILLING_STATUS_NOT_BILLING, REMIND_STATUS_0, PAYMENT_NOT_YET),
            Billing("190908000004", "mt00000001", BILLING_STATUS_NOT_BILLING, REMIND_STATUS_0, PAYMENT_NOT_YET)
    )

    /**
     * 指定された注文IDの請求リストを取得します
     */
    fun getBillingsByOrderId(orderIds: List<String>): MutableList<Billing> {
        return this.billings.filter { billings: Billing -> orderIds.contains(billings.orderId) }.toMutableList()
    }

    /**
     * 指定された注文IDの請求を初回請求にします
     */
    fun updateBillingStatusToFirst(orderIds: List<String>): MutableList<Billing> {
        return getBillingsByOrderId(orderIds).map { billing: Billing -> Billing(billing.orderId, billing.merchantId, BILLING_STATUS_BILLING_1, REMIND_STATUS_0, PAYMENT_NOT_YET) }.toMutableList()
    }
}