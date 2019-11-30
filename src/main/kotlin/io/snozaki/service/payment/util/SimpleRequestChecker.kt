package io.snozaki.service.payment.util

import io.snozaki.service.payment.dto.AbstractRequest
import io.snozaki.service.payment.dto.AbstractRequestElement

fun <T : AbstractRequest<E>, E: AbstractRequestElement> isValidRequest(t: T): Boolean {

    if ( !isValidMerchantId(t.merchantId) ) return false

    if ( !areValidIds(t.objects) ) return false

    return true

}

/**
 * 半角英数字パターン
 */
const val REGEX_HALFSIZED_ALPHABET_NUMBER = "/^[0-9a-zA-Z]*\$/"

/**
 * 加盟店IDチェック
 */
private fun isValidMerchantId(merchantId: String): Boolean {
    // 桁数チェック
    if(merchantId.chars().count().toInt() >= 40) return false

    // 半角英数字パターン
    val regex = Regex(pattern = REGEX_HALFSIZED_ALPHABET_NUMBER)
    if (!regex.containsMatchIn(merchantId)) return false

    return true
}

/**
 * 処理対象IDチェック
 */
private fun <E : AbstractRequestElement> areValidIds(ids: MutableList<E>): Boolean {
    // 要素数1000以下
    if(ids.count() >= 1000) return false

    // 各IDの半角英数字、および桁数の判定
    var orderIds: List<String> = ids.flatMap { id: E -> listOf(id.orderId) }

    val regex = Regex(pattern = REGEX_HALFSIZED_ALPHABET_NUMBER)
    var count: Int = orderIds
            .filterNot { id: String -> regex.containsMatchIn(id) }
            .filterNot { id: String -> id.length != 12 }
            .count()
    if(count > 0) return false

    return true
}
