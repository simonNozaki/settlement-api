package io.snozaki.service.payment.entity.order

data class Customer (
        var id: String?,
        var name: String?,
        var phoneNumber: String?,
        var mail: String?,
        var address: String?
)