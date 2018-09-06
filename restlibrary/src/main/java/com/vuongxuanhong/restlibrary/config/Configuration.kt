package com.vuongxuanhong.restlibrary.config

import okhttp3.Authenticator

/**
 * Created by vuongxuanhong on 9/5/18. HappyCoding!
 */
interface Configuration {

    /**
     * cache repsonse timeout in seconds
     */
    fun cacheTimeoutInSecs(): Int

    /**
     * cache size in Megabytes
     */
    fun cacheSizeInMB(): Int

    /**
     * connection timeout in seconds for both make connection and read the response
     */
    fun connectionTimeoutInSecs(): Int

    fun requestLogLevel(): Level

    fun authenticator(): Authenticator

    /**
     * copy from HttpLoggingInterceptor.Level
     */
    enum class Level {
        /** No logs.  */
        NONE,
        /**
         * Logs request and response lines.
         *
         *
         * Example:
         * <pre>`--> POST /greeting http/1.1 (3-byte body)
         *
         * <-- 200 OK (22ms, 6-byte body)
        `</pre> *
         */
        BASIC,
        /**
         * Logs request and response lines and their respective headers.
         *
         *
         * Example:
         * <pre>`--> POST /greeting http/1.1
         * Host: example.com
         * Content-Type: plain/text
         * Content-Length: 3
         * --> END POST
         *
         * <-- 200 OK (22ms)
         * Content-Type: plain/text
         * Content-Length: 6
         * <-- END HTTP
        `</pre> *
         */
        HEADERS,
        /**
         * Logs request and response lines and their respective headers and bodies (if present).
         *
         *
         * Example:
         * <pre>`--> POST /greeting http/1.1
         * Host: example.com
         * Content-Type: plain/text
         * Content-Length: 3
         *
         * Hi?
         * --> END POST
         *
         * <-- 200 OK (22ms)
         * Content-Type: plain/text
         * Content-Length: 6
         *
         * Hello!
         * <-- END HTTP
        `</pre> *
         */
        BODY
    }
}