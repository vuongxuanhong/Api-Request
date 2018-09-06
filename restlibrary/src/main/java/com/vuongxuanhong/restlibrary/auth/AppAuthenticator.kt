package com.vuongxuanhong.restlibrary.auth

import com.vuongxuanhong.restlibrary.service.ApiService
import okhttp3.Authenticator
import okhttp3.Response

/**
 * Created by xuanhong on 8/29/18. HappyCoding!
 */
abstract class AppAuthenticator : Authenticator {

    lateinit var service: ApiService

    protected fun responseCount(response: Response): Int {
        var result = 1
        var failed: Response? = response
        while (failed?.priorResponse() != null) {
            result++
            failed = failed.priorResponse()
        }

        return result
    }

}