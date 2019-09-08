package io.snozaki.service.payment.config

import io.snozaki.service.payment.config.logger.AppErrorLogger
import io.snozaki.service.payment.config.logger.AppTraceLogger
import org.slf4j.Logger
import org.slf4j.LoggerFactory


private val appTraceLogger: Logger = LoggerFactory.getLogger(AppTraceLogger().javaClass)
private val appErrorLogger: Logger = LoggerFactory.getLogger(AppErrorLogger().javaClass)

fun trace(msg: String){
    appTraceLogger.info(msg)
}

fun error(th: Throwable){
    appErrorLogger.error("処理中にエラーが発生しました : ", th)
}
