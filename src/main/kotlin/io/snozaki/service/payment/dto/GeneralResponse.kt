package io.snozaki.service.payment.dto

data class GeneralResponse<T> (var ok: Boolean, var message: String, var value: T) {

    class Builder<T> {
        var ok: Boolean? = true
        var message: String? = null
        var value: T? = null

        fun <S> build(): GeneralResponse<T> = GeneralResponse(
                ok = requireNotNull(this.ok),
                message = requireNotNull(this.message),
                value = requireNotNull(this.value)
        )

        companion object {
            fun <T> build(f: Builder<T>.() -> Unit): GeneralResponse<T> {
                val builder = Builder<T>()
                builder.f()
                return builder.build<T>()
            }
        }
    }
}
