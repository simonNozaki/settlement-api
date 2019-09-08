package io.snozaki.service.payment

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(
		"io.snozaki.service.payment.controller",
		"io.snozaki.service.payment.service",
		"io.snozaki.service.payment.repository"
)
class PaymentApplication

fun main(args: Array<String>) {
	runApplication<PaymentApplication>(*args)
}
