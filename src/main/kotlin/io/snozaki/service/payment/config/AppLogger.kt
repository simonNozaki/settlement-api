package io.snozaki.service.payment.config

import io.snozaki.service.payment.config.logger.AppErrorLogger
import io.snozaki.service.payment.config.logger.AppTraceLogger
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*

/**
 * ログファクトリクラス。
 */
class AppLogger {

    companion object {

        private val appTraceLogger: Logger = LoggerFactory.getLogger(AppTraceLogger().javaClass)
        private val appErrorLogger: Logger = LoggerFactory.getLogger(AppErrorLogger().javaClass)
        private val APP_NAME = "settlement-api"

        fun trace(msg: String) {
            appTraceLogger.info(concatThreadInfo() + msg)
        }

        fun error(th: Throwable) {
            appErrorLogger.error(concatThreadInfo() + "処理中にエラーが発生しました : ", th)
        }

        private fun concatThreadInfo(): String = "${Date().time} [ ${Thread.currentThread().name} ] $APP_NAME ${Throwable().stackTrace[2].className}#${Throwable().stackTrace[2].methodName}"
    }
}
