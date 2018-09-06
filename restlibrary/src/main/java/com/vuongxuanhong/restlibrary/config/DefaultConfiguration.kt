package com.vuongxuanhong.restlibrary.config

import com.vuongxuanhong.restlibrary.auth.DoNothingAuthenticator
import okhttp3.Authenticator

/**
 * Created by vuongxuanhong on 9/5/18. HappyCoding!
 */
class DefaultConfiguration : Configuration {
    override fun authenticator(): Authenticator {
        return DoNothingAuthenticator()
    }

    override fun cacheTimeoutInSecs(): Int {
        return 60
    }

    override fun cacheSizeInMB(): Int {
        return 10
    }

    override fun connectionTimeoutInSecs(): Int {
        return 30
    }

    override fun requestLogLevel(): Configuration.Level {
        return Configuration.Level.BODY
    }

}