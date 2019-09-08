package io.snozaki.service.payment.consts.model

// -----------------------------
// 請求ステータス
// -----------------------------
/**
 * 請求前
 */
const val BILLING_STATUS_NOT_BILLING: String = "0"

/**
 * 請求一回目
 */
const val BILLING_STATUS_BILLING_1: String = "1"

/**
 * 請求二回目
 */
const val BILLING_STATUS_BILLING_2: String = "2"

/**
 * 請求三回目
 */
const val BILLING_STATUS_BILLING_3: String = "3"

/**
 * 請求四回目
 */
const val BILLING_STATUS_BILLING_4: String = "4"

/**
 * 請求五回目
 */
const val BILLING_STATUS_DEBT: String = "5"

// -----------------------------
// 督促ステータス
// -----------------------------
const val REMIND_STATUS_0: String = "0"

const val REMIND_STATUS_1: String = "1"

const val REMIND_STATUS_2: String = "2"

const val REMIND_STATUS_3: String = "3"

const val REMIND_STATUS_4: String = "4"

const val REMIND_STATUS_5: String = "5"

// -----------------------------
// 入金状態
// -----------------------------
const val PAYMENT_NOT_YET: String = "0"

const val PAYMENT_FULL: String = "1"

const val PAYMENT_UNDERPAYMENT: String = "2"

const val PAYMENT_OVERPAYMENT: String = "3"

const val PAYMENT_MUTIPLEPAYMENT: String = "4"

const val PAYMENT_CANCELED_ORDER: String = "5"