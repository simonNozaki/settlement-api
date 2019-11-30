package io.snozaki.service.payment.dto

/**
 * リクエストの抽象クラス
 */
abstract class AbstractRequest<E> {

    /**
     * 加盟店ID
     */
    abstract var merchantId: String

    /**
     * 処理対象のID
     */
    abstract var objects: MutableList<E>
}