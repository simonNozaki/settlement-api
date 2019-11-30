package io.snozaki.service.payment.dto

/**
 * レスポンスの抽象クラス
 */
abstract class AbstractResponse<E> {

    /**
     * 処理対象のID
     */
    abstract var ids: MutableList<E>
}