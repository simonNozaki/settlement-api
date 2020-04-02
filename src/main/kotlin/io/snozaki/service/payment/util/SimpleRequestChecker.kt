package io.snozaki.service.payment.util

import io.snozaki.service.payment.dto.AbstractRequest
import io.snozaki.service.payment.dto.AbstractRequestElement

/**
 * リクエストの正常性を判定します。加盟店IDと注文IDリストがすべて正しく設定されていることを確認します。
 * @param t リクエストオブジェクト。@code{AbstractRequest}の継承した具象クラスを受け付けます。
 * @return 真偽値
 */
fun <T : AbstractRequest<E>, E: AbstractRequestElement> isValidRequest(t: T): Boolean {

    if ( !t.merchantId.isValidMerchantId() ) return false

    if ( !areValidIds(t.objects) ) return false

    return true

}

/**
 * 半角英数字パターン
 */
private const val REGEX_HALFSIZED_ALPHABET_NUMBER = "^mt[0-9]+"

/**
 * 加盟店IDチェック
 */
fun String.isValidMerchantId(): Boolean {
    // 空文字チェック
    if(this.isBlank()) return false

    // 桁数チェック
    if(this.chars().count().toInt() > 40) return false

    // 半角英数字パターン
    val regex = Regex(pattern = REGEX_HALFSIZED_ALPHABET_NUMBER)
    if (!regex.matches(this)) return false

    return true
}

/**
 * 処理対象IDチェック
 */
private fun <E : AbstractRequestElement> areValidIds(ids: MutableList<E>): Boolean {
    // 要素数1000以下
    if(ids.count() > 1000) return false

    // 各IDの半角英数字、および桁数の判定
    val orderIds: List<String> = ids.flatMap { id: E -> listOf(id.orderId) }

    val regex = Regex(pattern = REGEX_HALFSIZED_ALPHABET_NUMBER)
    val count: Int = orderIds
            .filterNot { id: String -> regex.containsMatchIn(id) }
            .filterNot { id: String -> id.length != 12 }
            .count()
    if(count > 0) return false

    return true
}
